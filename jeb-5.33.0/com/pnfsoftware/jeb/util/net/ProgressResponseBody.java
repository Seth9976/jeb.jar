package com.pnfsoftware.jeb.util.net;

import com.pnfsoftware.jeb.util.base.IProgressCallback;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

class ProgressResponseBody extends ResponseBody {
   private final ResponseBody responseBody;
   private final IProgressCallback progressListener;
   private BufferedSource bufferedSource;

   ProgressResponseBody(ResponseBody var1, IProgressCallback var2) {
      this.responseBody = var1;
      this.progressListener = var2;
   }

   public MediaType contentType() {
      return this.responseBody.contentType();
   }

   public long contentLength() {
      return this.responseBody.contentLength();
   }

   public BufferedSource source() {
      if (this.bufferedSource == null) {
         this.bufferedSource = Okio.buffer(this.source(this.responseBody.source()));
      }

      return this.bufferedSource;
   }

   private Source source(Source var1) {
      return new ProgressResponseBody$1(this, var1);
   }
}
