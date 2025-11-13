package com.pnfsoftware.jeb.util.net;

import java.io.IOException;
import okio.Buffer;
import okio.ForwardingSource;
import okio.Source;

class ProgressResponseBody$1 extends ForwardingSource {
   long totalBytesRead;

   ProgressResponseBody$1(ProgressResponseBody var1, Source var2) {
      super(var2);
      this.this$0 = var1;
      this.totalBytesRead = 0L;
   }

   public long read(Buffer var1, long var2) throws IOException {
      long var4 = super.read(var1, var2);
      this.totalBytesRead += var4 != -1L ? var4 : 0L;
      if (!this.this$0.progressListener.isInitialized()) {
         this.this$0.progressListener.setTotal(this.this$0.responseBody.contentLength());
      }

      this.this$0.progressListener.setCurrent(this.totalBytesRead);
      return var4;
   }
}
