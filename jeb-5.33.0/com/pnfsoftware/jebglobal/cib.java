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

public class cib implements Iterator {
   private static final ILogger wS = GlobalLog.getLogger(cib.class);
   SeekableByteChannel pC;
   ChannelHelper A;
   Set kS = new HashSet();

   public cib(SeekableByteChannel var1) {
      this.pC = var1;
      this.A = new ChannelHelper(var1, Endianness.LITTLE_ENDIAN);
   }

   @Override
   public boolean hasNext() {
      try {
         return this.pC.position() + 4L <= this.pC.size();
      } catch (IOException var1) {
         return false;
      }
   }

   public cii pC() {
      try {
         int var1 = this.A.getShort() & '\uffff';
         int var2 = this.A.getShort() & '\uffff';
         this.kS.add(var2);
         int var3 = var1 - 2;
         cii var4 = new cii(var2, var3, (int)this.pC.position());
         this.pC.position(this.pC.position() + var3);
         return var4;
      } catch (IOException var5) {
         throw new NoSuchElementException("An I/O error has occurred");
      }
   }

   public cij pC(cii var1) throws IOException {
      long var2 = this.pC.position();
      this.pC.position(var1.kS);

      cil var5;
      try {
         ByteBuffer var4 = ChannelUtil.read(this.pC, var1.kS, var1.A, false);
         switch (var1.pC) {
            case 4359:
               return cid.pC(var4);
            case 4360:
               return cim.pC(var4);
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
               return cie.pC(var4);
            case 4366:
               return cih.pC(var4);
            case 4370:
            case 4371:
               return cik.pC(var4);
            case 4389:
            case 4391:
               return cig.pC(var4);
            case 4392:
               return cic.pC(var4);
            case 4393:
         }

         var5 = cil.pC(var4);
      } finally {
         this.pC.position(var2);
      }

      return var5;
   }
}
