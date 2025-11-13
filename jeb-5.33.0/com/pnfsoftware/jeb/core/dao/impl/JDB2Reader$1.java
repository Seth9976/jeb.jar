package com.pnfsoftware.jeb.core.dao.impl;

import java.io.IOException;
import java.io.InputStream;

class JDB2Reader$1 extends InputStream {
   JDB2Reader$1(JDB2Reader var1, long var2) {
      this.this$0 = var1;
      this.val$maxPointer = var2;
   }

   @Override
   public int read() throws IOException {
      return this.this$0.fa.getFilePointer() >= this.val$maxPointer ? -1 : this.this$0.fa.read();
   }

   @Override
   public int read(byte[] var1, int var2, int var3) throws IOException {
      if (var3 == 0) {
         return 0;
      } else {
         int var4 = (int)(this.val$maxPointer - this.this$0.fa.getFilePointer());
         if (var4 <= 0) {
            return -1;
         } else {
            if (var4 < var3) {
               var3 = var4;
            }

            return this.this$0.fa.read(var1, var2, var3);
         }
      }
   }

   @Override
   public int available() throws IOException {
      return (int)(this.val$maxPointer - this.this$0.fa.getFilePointer());
   }
}
