package com.pnfsoftware.jeb.core.dao.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.CRC32;

class JDB2Writer$1 extends OutputStream {
   CRC32 crc;

   JDB2Writer$1(JDB2Writer var1, boolean var2, JDB2Writer.Mark var3) {
      this.this$0 = var1;
      this.val$computeCrc = var2;
      this.val$mark = var3;
      this.crc = this.val$computeCrc ? this.val$mark.crc : null;
   }

   @Override
   public void write(int var1) throws IOException {
      this.this$0.fa.write(var1);
      if (this.crc != null) {
         this.crc.update(var1);
      }
   }

   @Override
   public void write(byte[] var1) throws IOException {
      this.this$0.fa.write(var1);
      if (this.crc != null) {
         this.crc.update(var1);
      }
   }

   @Override
   public void write(byte[] var1, int var2, int var3) throws IOException {
      this.this$0.fa.write(var1, var2, var3);
      if (this.crc != null) {
         this.crc.update(var1, var2, var3);
      }
   }
}
