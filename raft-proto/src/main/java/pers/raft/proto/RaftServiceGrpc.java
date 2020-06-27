package pers.raft.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.17.1)",
    comments = "Source: raft.proto")
public final class RaftServiceGrpc {

  private RaftServiceGrpc() {}

  public static final String SERVICE_NAME = "RaftService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<pers.raft.proto.RaftProto.VoteRequest,
      pers.raft.proto.RaftProto.VoteResponse> getRequestVoteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "requestVote",
      requestType = pers.raft.proto.RaftProto.VoteRequest.class,
      responseType = pers.raft.proto.RaftProto.VoteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pers.raft.proto.RaftProto.VoteRequest,
      pers.raft.proto.RaftProto.VoteResponse> getRequestVoteMethod() {
    io.grpc.MethodDescriptor<pers.raft.proto.RaftProto.VoteRequest, pers.raft.proto.RaftProto.VoteResponse> getRequestVoteMethod;
    if ((getRequestVoteMethod = RaftServiceGrpc.getRequestVoteMethod) == null) {
      synchronized (RaftServiceGrpc.class) {
        if ((getRequestVoteMethod = RaftServiceGrpc.getRequestVoteMethod) == null) {
          RaftServiceGrpc.getRequestVoteMethod = getRequestVoteMethod = 
              io.grpc.MethodDescriptor.<pers.raft.proto.RaftProto.VoteRequest, pers.raft.proto.RaftProto.VoteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "RaftService", "requestVote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.raft.proto.RaftProto.VoteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.raft.proto.RaftProto.VoteResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new RaftServiceMethodDescriptorSupplier("requestVote"))
                  .build();
          }
        }
     }
     return getRequestVoteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pers.raft.proto.RaftProto.AppendEntriesRequest,
      pers.raft.proto.RaftProto.AppendEntriesResponse> getAppendEntriesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "appendEntries",
      requestType = pers.raft.proto.RaftProto.AppendEntriesRequest.class,
      responseType = pers.raft.proto.RaftProto.AppendEntriesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pers.raft.proto.RaftProto.AppendEntriesRequest,
      pers.raft.proto.RaftProto.AppendEntriesResponse> getAppendEntriesMethod() {
    io.grpc.MethodDescriptor<pers.raft.proto.RaftProto.AppendEntriesRequest, pers.raft.proto.RaftProto.AppendEntriesResponse> getAppendEntriesMethod;
    if ((getAppendEntriesMethod = RaftServiceGrpc.getAppendEntriesMethod) == null) {
      synchronized (RaftServiceGrpc.class) {
        if ((getAppendEntriesMethod = RaftServiceGrpc.getAppendEntriesMethod) == null) {
          RaftServiceGrpc.getAppendEntriesMethod = getAppendEntriesMethod = 
              io.grpc.MethodDescriptor.<pers.raft.proto.RaftProto.AppendEntriesRequest, pers.raft.proto.RaftProto.AppendEntriesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "RaftService", "appendEntries"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.raft.proto.RaftProto.AppendEntriesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.raft.proto.RaftProto.AppendEntriesResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new RaftServiceMethodDescriptorSupplier("appendEntries"))
                  .build();
          }
        }
     }
     return getAppendEntriesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pers.raft.proto.RaftProto.VoteRequest,
      pers.raft.proto.RaftProto.VoteResponse> getInstallSnapshotMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "installSnapshot",
      requestType = pers.raft.proto.RaftProto.VoteRequest.class,
      responseType = pers.raft.proto.RaftProto.VoteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pers.raft.proto.RaftProto.VoteRequest,
      pers.raft.proto.RaftProto.VoteResponse> getInstallSnapshotMethod() {
    io.grpc.MethodDescriptor<pers.raft.proto.RaftProto.VoteRequest, pers.raft.proto.RaftProto.VoteResponse> getInstallSnapshotMethod;
    if ((getInstallSnapshotMethod = RaftServiceGrpc.getInstallSnapshotMethod) == null) {
      synchronized (RaftServiceGrpc.class) {
        if ((getInstallSnapshotMethod = RaftServiceGrpc.getInstallSnapshotMethod) == null) {
          RaftServiceGrpc.getInstallSnapshotMethod = getInstallSnapshotMethod = 
              io.grpc.MethodDescriptor.<pers.raft.proto.RaftProto.VoteRequest, pers.raft.proto.RaftProto.VoteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "RaftService", "installSnapshot"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.raft.proto.RaftProto.VoteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pers.raft.proto.RaftProto.VoteResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new RaftServiceMethodDescriptorSupplier("installSnapshot"))
                  .build();
          }
        }
     }
     return getInstallSnapshotMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RaftServiceStub newStub(io.grpc.Channel channel) {
    return new RaftServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RaftServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new RaftServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RaftServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new RaftServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class RaftServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     **
     * 选举
     * </pre>
     */
    public void requestVote(pers.raft.proto.RaftProto.VoteRequest request,
        io.grpc.stub.StreamObserver<pers.raft.proto.RaftProto.VoteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestVoteMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * 附加日志
     * </pre>
     */
    public void appendEntries(pers.raft.proto.RaftProto.AppendEntriesRequest request,
        io.grpc.stub.StreamObserver<pers.raft.proto.RaftProto.AppendEntriesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAppendEntriesMethod(), responseObserver);
    }

    /**
     */
    public void installSnapshot(pers.raft.proto.RaftProto.VoteRequest request,
        io.grpc.stub.StreamObserver<pers.raft.proto.RaftProto.VoteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getInstallSnapshotMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRequestVoteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pers.raft.proto.RaftProto.VoteRequest,
                pers.raft.proto.RaftProto.VoteResponse>(
                  this, METHODID_REQUEST_VOTE)))
          .addMethod(
            getAppendEntriesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pers.raft.proto.RaftProto.AppendEntriesRequest,
                pers.raft.proto.RaftProto.AppendEntriesResponse>(
                  this, METHODID_APPEND_ENTRIES)))
          .addMethod(
            getInstallSnapshotMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pers.raft.proto.RaftProto.VoteRequest,
                pers.raft.proto.RaftProto.VoteResponse>(
                  this, METHODID_INSTALL_SNAPSHOT)))
          .build();
    }
  }

  /**
   */
  public static final class RaftServiceStub extends io.grpc.stub.AbstractStub<RaftServiceStub> {
    private RaftServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RaftServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RaftServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RaftServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * 选举
     * </pre>
     */
    public void requestVote(pers.raft.proto.RaftProto.VoteRequest request,
        io.grpc.stub.StreamObserver<pers.raft.proto.RaftProto.VoteResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestVoteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * 附加日志
     * </pre>
     */
    public void appendEntries(pers.raft.proto.RaftProto.AppendEntriesRequest request,
        io.grpc.stub.StreamObserver<pers.raft.proto.RaftProto.AppendEntriesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAppendEntriesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void installSnapshot(pers.raft.proto.RaftProto.VoteRequest request,
        io.grpc.stub.StreamObserver<pers.raft.proto.RaftProto.VoteResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInstallSnapshotMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RaftServiceBlockingStub extends io.grpc.stub.AbstractStub<RaftServiceBlockingStub> {
    private RaftServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RaftServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RaftServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RaftServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * 选举
     * </pre>
     */
    public pers.raft.proto.RaftProto.VoteResponse requestVote(pers.raft.proto.RaftProto.VoteRequest request) {
      return blockingUnaryCall(
          getChannel(), getRequestVoteMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * 附加日志
     * </pre>
     */
    public pers.raft.proto.RaftProto.AppendEntriesResponse appendEntries(pers.raft.proto.RaftProto.AppendEntriesRequest request) {
      return blockingUnaryCall(
          getChannel(), getAppendEntriesMethod(), getCallOptions(), request);
    }

    /**
     */
    public pers.raft.proto.RaftProto.VoteResponse installSnapshot(pers.raft.proto.RaftProto.VoteRequest request) {
      return blockingUnaryCall(
          getChannel(), getInstallSnapshotMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RaftServiceFutureStub extends io.grpc.stub.AbstractStub<RaftServiceFutureStub> {
    private RaftServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RaftServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RaftServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RaftServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * 选举
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<pers.raft.proto.RaftProto.VoteResponse> requestVote(
        pers.raft.proto.RaftProto.VoteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestVoteMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * 附加日志
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<pers.raft.proto.RaftProto.AppendEntriesResponse> appendEntries(
        pers.raft.proto.RaftProto.AppendEntriesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAppendEntriesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pers.raft.proto.RaftProto.VoteResponse> installSnapshot(
        pers.raft.proto.RaftProto.VoteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getInstallSnapshotMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REQUEST_VOTE = 0;
  private static final int METHODID_APPEND_ENTRIES = 1;
  private static final int METHODID_INSTALL_SNAPSHOT = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RaftServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RaftServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REQUEST_VOTE:
          serviceImpl.requestVote((pers.raft.proto.RaftProto.VoteRequest) request,
              (io.grpc.stub.StreamObserver<pers.raft.proto.RaftProto.VoteResponse>) responseObserver);
          break;
        case METHODID_APPEND_ENTRIES:
          serviceImpl.appendEntries((pers.raft.proto.RaftProto.AppendEntriesRequest) request,
              (io.grpc.stub.StreamObserver<pers.raft.proto.RaftProto.AppendEntriesResponse>) responseObserver);
          break;
        case METHODID_INSTALL_SNAPSHOT:
          serviceImpl.installSnapshot((pers.raft.proto.RaftProto.VoteRequest) request,
              (io.grpc.stub.StreamObserver<pers.raft.proto.RaftProto.VoteResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RaftServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RaftServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return pers.raft.proto.RaftProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RaftService");
    }
  }

  private static final class RaftServiceFileDescriptorSupplier
      extends RaftServiceBaseDescriptorSupplier {
    RaftServiceFileDescriptorSupplier() {}
  }

  private static final class RaftServiceMethodDescriptorSupplier
      extends RaftServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RaftServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RaftServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RaftServiceFileDescriptorSupplier())
              .addMethod(getRequestVoteMethod())
              .addMethod(getAppendEntriesMethod())
              .addMethod(getInstallSnapshotMethod())
              .build();
        }
      }
    }
    return result;
  }
}
