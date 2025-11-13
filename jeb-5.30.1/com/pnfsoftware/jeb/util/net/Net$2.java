package com.pnfsoftware.jeb.util.net;

import com.pnfsoftware.jeb.util.base.IProgressCallback;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.Interceptor.Chain;

class Net$2 implements Interceptor {
   Net$2(Net var1, IProgressCallback var2) {
      this.this$0 = var1;
      this.val$progressCallback = var2;
   }

   public Response intercept(Chain var1) throws IOException {
      Response var2 = var1.proceed(var1.request());
      return var2.newBuilder().body(new ProgressResponseBody(var2.body(), this.val$progressCallback)).build();
   }
}
