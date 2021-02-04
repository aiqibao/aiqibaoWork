package com.aiqibao.rpc;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.junit.Test;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:aiqibao
 * @Date:2021/1/28 15:33
 * Best wish!
 */

/**
    rpc：像本地方法一样调用远程的方法
 */
public class MyRPCTest {
    @Test
    public void startServer(){
        MyCar myCar = new MyCar();
        MyFly myFly = new MyFly();
        Dispather dispather = new Dispather();
        dispather.register(Car.class.getName(),myCar);
        dispather.register(MyFly.class.getName(),myFly);
        NioEventLoopGroup boss = new NioEventLoopGroup(20);
        NioEventLoopGroup work = boss ;
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        ChannelFuture bind = serverBootstrap.group(boss, work)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        System.out.println("server accept client port:" + ch.remoteAddress().getPort());
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new ServerDecode()).addLast(new SeverRequestHander(dispather));
                    }
                }).bind(new InetSocketAddress("localhost", 9090));
        try {
            bind.sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void get(){
        new Thread(()->{
            startServer();
        }).start();
        System.out.println("server started ......");

        AtomicInteger atomicInteger = new AtomicInteger(0);
        int size = 50 ;
        Thread[] threads = new Thread[50];
        for (int i = 0; i < size ; i++) {
            threads[i] = new Thread(()->{
                Car car = proxyGet(Car.class) ;
                String arg = "hello" + atomicInteger.incrementAndGet() ;
                String res = car.setCarName(arg);
                System.out.println("client over msg:" + res +"src arg: " + arg);
            });
        }
        for (Thread t:threads){
            t.start();
        }
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private <T>T proxyGet(Class<T> interfaceInfo) {
        ClassLoader classLoader = interfaceInfo.getClassLoader();
        Class<?>[] interfaces = {interfaceInfo} ;
        return (T)Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String name = interfaceInfo.getName() ;
                String methodName = method.getName() ;
                Class<?>[] argsType = method.getParameterTypes() ;
                MyContent myContent = new MyContent() ;
                myContent.setName(name);
                myContent.setMethodName(methodName);
                myContent.setParameterTypes(argsType);
                myContent.setArgs(args);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
                objectOutputStream.writeObject(myContent);
                byte[] msgBody = out.toByteArray();
                MyHead header = createHeader(msgBody);
                out.reset();
                objectOutputStream = new ObjectOutputStream(out) ;
                objectOutputStream.writeObject(header);
                byte[] msgHeader = out.toByteArray();
                System.out.println("old:::"+msgHeader.length);
                //获得连接
                ClientFactory factory = ClientFactory.getFactory();
                NioSocketChannel client = factory.getClient(new InetSocketAddress("localhost", 9090));

                ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(msgHeader.length+ msgBody.length);

                long id = header.getRequest();
                CompletableFuture<String> res = new CompletableFuture<>();
                ResponseMappingCallback.addCallBack(id,res) ;
                byteBuf.writeBytes(msgHeader) ;
                byteBuf.writeBytes(msgBody) ;
                ChannelFuture channelFuture = client.writeAndFlush(byteBuf);
                channelFuture.sync() ;
                return res.get() ;
            }
        });

    }



    private MyHead createHeader(byte[] msgBody) {
        MyHead myHead = new MyHead();
        int flag = 0x14141414 ;
        long requestId = Math.abs(UUID.randomUUID().getLeastSignificantBits()) ;
        myHead.setFlag(flag);
        myHead.setLength(msgBody.length);
        myHead.setRequest(requestId);
        return myHead ;
    }
}
class MyContent implements Serializable {
    String name ;
    String methodName ;
    Class<?>[] parameterTypes ;
    Object[] args ;
    String res ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}
class MyHead implements Serializable{
    int flag ;
    long request ;
    long length ;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public long getRequest() {
        return request;
    }

    public void setRequest(long request) {
        this.request = request;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }
}
interface Car{
    String getCarName();
    String setCarName(String name) ;
}
interface Fly{
    void getFlyName();
}
class ClientFactory{
    int poolSize = 1 ;
    NioEventLoopGroup clientWorker ;
    Random random = new Random() ;
    public static final ClientFactory factory ;
    static {
        factory = new ClientFactory() ;
    }
    static ClientFactory getFactory(){
        return factory ;
    }

    ConcurrentHashMap<InetSocketAddress,ClientPool> outPools = new ConcurrentHashMap<InetSocketAddress, ClientPool>() ;
    public synchronized NioSocketChannel getClient(InetSocketAddress InetSocketAddress){
        ClientPool clientPool = outPools.get(InetSocketAddress);
        if (clientPool == null){
            outPools.putIfAbsent(InetSocketAddress,new ClientPool(poolSize)) ;
            clientPool = outPools.get(InetSocketAddress) ;
        }
        int i = random.nextInt(poolSize);
        if (clientPool.clients[i] != null && clientPool.clients[i].isActive()){
            return clientPool.clients[i] ;
        }
        synchronized (clientPool.lock[i]){
            return clientPool.clients[i] = create(InetSocketAddress) ;
        }
    }

    private NioSocketChannel create(InetSocketAddress inetSocketAddress) {
        //基于netty的客户端创建
        clientWorker = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap();
        ChannelFuture connect = bootstrap.group(clientWorker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new ServerDecode()).addLast(new ClientResponses());
                    }
                }).connect(inetSocketAddress);
        try {
            NioSocketChannel client = (NioSocketChannel)connect.sync().channel();
            return client ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    return null ;
    }
}
class ClientPool{
    NioSocketChannel[] clients ;
    Object[] lock ;
    ClientPool(int poolSize){
        clients = new NioSocketChannel[poolSize];
        lock = new Object[poolSize] ;
        for (int i = 0; i < poolSize; i++) {
            lock[i] = new Object() ;
        }
    }
}
class ServerDecode extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        while (byteBuf.readableBytes()>=89){
            byte[] bytes = new byte[89];
            byteBuf.getBytes(byteBuf.readerIndex(),bytes) ;
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            ObjectInputStream oin = new ObjectInputStream(in);
            MyHead head = (MyHead)oin.readObject();
            if (byteBuf.readableBytes()>=head.getLength()){
                byteBuf.readBytes(89) ;
                byte[] data = new byte[(int) head.getLength()];
                byteBuf.readBytes(data) ;
                ByteArrayInputStream din = new ByteArrayInputStream(data);
                ObjectInputStream doin = new ObjectInputStream(din);
                if (head.getFlag() == 0x14141414){
                    MyContent myContent = (MyContent)doin.readObject();
                    list.add(new Packmsg(head,myContent)) ;
                }else if (head.getFlag() == 0x14141424){
                    MyContent myContent = (MyContent)doin.readObject();
                    list.add(new Packmsg(head,myContent)) ;
                }
            }else{
                break;
            }
        }
    }
}
class ClientResponses extends ChannelInboundHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Packmsg packmsg = (Packmsg) msg ;
        ResponseMappingCallback.runCallBack(packmsg) ;
    }
}
class ResponseMappingCallback{
    static ConcurrentHashMap<Long,CompletableFuture> mapping = new ConcurrentHashMap<>() ;

    public static void addCallBack(long id, CompletableFuture<String> res) {
        mapping.putIfAbsent(id,res) ;
    }
    public static void runCallBack(Packmsg packmsg){
        CompletableFuture cf = mapping.get(packmsg.head.getRequest());
        cf.complete(packmsg.getContent().getRes()) ;
        removeCabllBack(packmsg.head.getRequest());
    }
    public static void removeCabllBack(long id){
        mapping.remove(id);
    }
}
class MyCar implements Car{


    @Override
    public String getCarName() {
        System.out.println("aiqibao");
        return "aiqibao";
    }

    @Override
    public String setCarName(String name) {
        return name;
    }
}
class MyFly implements Fly{

    @Override
    public void getFlyName() {
        System.out.println("boyin");
    }
}
class Dispather{
    public static ConcurrentHashMap<String,Object> invokeMap = new ConcurrentHashMap<>() ;
    public void register(String k,Object v){
        invokeMap.put(k,v) ;
    }
    public Object get(String k){
        return invokeMap.get(k) ;
    }
}
class SeverRequestHander extends ChannelInboundHandlerAdapter{
    Dispather dis ;
    public SeverRequestHander(Dispather dis){
        this.dis = dis ;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Packmsg packmsg = (Packmsg) msg ;
        super.channelRead(ctx, msg);
        String ioThreadName = Thread.currentThread().getName();
        ctx.executor().execute(() -> {
            String name = packmsg.getContent().getName();
            String methodName = packmsg.getContent().getMethodName();
            Class<?>[] parameterTypes = packmsg.getContent().getParameterTypes();
            Object o = dis.get(name);
            Class<?> clazz = o.getClass();
            Object res = null ;
            try {
                Method method = clazz.getMethod(methodName, parameterTypes);
                res = method.invoke(o,packmsg.getContent().getArgs()) ;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            MyContent myContent = new MyContent();
            myContent.setRes((String)res);

            MyHead head = new MyHead() ;
            byte[] content = SerDerUtil.ser(myContent);
            head.setRequest(packmsg.getHead().getRequest());
            head.setFlag(0x14141424);
            head.setLength(content.length);
            byte[] header = SerDerUtil.ser(head);
            ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(header.length + content.length);
            byteBuf.writeBytes(header);
            byteBuf.writeBytes(content);
            ctx.writeAndFlush(byteBuf);
        });
    }
}
class Packmsg{
    MyHead head ;
    MyContent content ;

    public Packmsg(MyHead head, MyContent content) {
        this.head = head;
        this.content = content;
    }

    public MyHead getHead() {
        return head;
    }

    public void setHead(MyHead head) {
        this.head = head;
    }

    public MyContent getContent() {
        return content;
    }

    public void setContent(MyContent content) {
        this.content = content;
    }
}
class SerDerUtil{
    static ByteArrayOutputStream out = new ByteArrayOutputStream() ;
    public static byte[] ser(Object msg){
        out.reset();
        ObjectOutputStream oout = null ;
        byte[] msgBody = null ;
        try {
            oout = new ObjectOutputStream(out) ;
            oout.writeObject(msg);
            msgBody = out.toByteArray() ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msgBody ;
    }
}
