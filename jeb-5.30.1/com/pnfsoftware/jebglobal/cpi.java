package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.io.ByteBufferUtil;
import com.pnfsoftware.jeb.util.io.ChannelHelper;
import com.pnfsoftware.jeb.util.io.ChannelUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class cpi implements Iterator {
   private static final ILogger Dw = GlobalLog.getLogger(cpi.class);
   SeekableByteChannel q;
   ChannelHelper RF;
   Set xK = new HashSet();

   public cpi(SeekableByteChannel var1) {
      this.q = var1;
      this.RF = new ChannelHelper(var1, Endianness.LITTLE_ENDIAN);
   }

   public void q() throws IOException {
      int var1;
      for (var1 = 0; this.q.position() + 4L <= this.q.size(); var1++) {
         int var2 = this.RF.getShort() & '\uffff';
         int var3 = this.RF.getShort() & '\uffff';
         this.xK.add(var3);
         int var4 = var2 - 2;
         this.q.position(this.q.position() + var4);
      }

      Object[] var10000 = new Object[]{var1};
      var10000 = new Object[]{Formatter.formatHexNumbers(this.xK)};
   }

   @Override
   public boolean hasNext() {
      try {
         return this.q.position() + 4L <= this.q.size();
      } catch (IOException var1) {
         return false;
      }
   }

   public cpq RF() {
      try {
         int var1 = this.RF.getShort() & '\uffff';
         int var2 = this.RF.getShort() & '\uffff';
         this.xK.add(var2);
         int var3 = var1 - 2;
         cpq var4 = new cpq(var2, var3, (int)this.q.position());
         this.q.position(this.q.position() + var3);
         return var4;
      } catch (IOException var5) {
         throw new NoSuchElementException("An I/O error has occurred");
      }
   }

   public cpr q(cpq var1) throws IOException {
      long var2 = this.q.position();
      this.q.position(var1.xK);

      cpt var5;
      try {
         ByteBuffer var4 = ChannelUtil.read(this.q, var1.xK, var1.RF, false);
         switch (var1.q) {
            case 4359:
               return cpl.q(var4);
            case 4360:
               return cpu.q(var4);
            case 4361:
            case 4362:
            case 4363:
            case 4367:
            case 4368:
            case 4369:
            case 4372:
            case 4373:
            case 4374:
            case 4375:
            case 4376:
            case 4377:
            case 4378:
            case 4379:
            case 4382:
            case 4383:
            case 4384:
            case 4385:
            case 4386:
            case 4387:
            case 4388:
            case 4390:
            default:
               Object[] var10000 = new Object[]{var1, Formatter.formatBinaryBlock(ByteBufferUtil.getBytes(var4))};
               return null;
            case 4364:
            case 4365:
            case 4380:
            case 4381:
               return cpm.q(var4);
            case 4366:
               return cpp.q(var4);
            case 4370:
            case 4371:
               return cps.q(var4);
            case 4389:
            case 4391:
               return cpo.q(var4);
            case 4392:
               return cpk.q(var4);
            case 4393:
         }

         var5 = cpt.q(var4);
      } finally {
         this.q.position(var2);
      }

      return var5;
   }
}
