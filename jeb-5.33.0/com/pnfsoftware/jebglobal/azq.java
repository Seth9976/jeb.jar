package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class azq implements Iterable {
   private static final ILogger A = GlobalLog.getLogger(azq.class);
   List pC;

   private azq(int var1) {
      this.pC = new ArrayList(var1);
   }

   @Override
   public Iterator iterator() {
      return this.pC.iterator();
   }

   public int pC() {
      return this.pC.size();
   }

   public azs pC(int var1) {
      return var1 > 0 && var1 <= this.pC.size() ? (azs)this.pC.get(var1 - 1) : null;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   static azq pC(ByteBuffer var0, int var1) {
      azq var2 = new azq(var1);
      byte[] var3 = new byte[0];

      for (int var4 = 1; var4 <= var1; var4++) {
         int var5 = var0.get() & 255;
         Object var6 = null;
         switch (bah.pC(var5)) {
            case A:
               var6 = new azx(var0.getInt());
               break;
            case kS:
               var6 = new azw(var0.getFloat());
               break;
            case wS:
               var6 = new azz(var0.getLong());
               break;
            case UT:
               var6 = new azu(var0.getDouble());
               break;
            case E:
               var6 = new azt(var0.getShort() & '\uffff');
               break;
            case ys:
               var6 = new azv(var0.getShort() & '\uffff', var0.getShort() & '\uffff');
               break;
            case ld:
               var6 = new bac(var0.getShort() & '\uffff', var0.getShort() & '\uffff');
               break;
            case gp:
               var6 = new azv(var0.getShort() & '\uffff', var0.getShort() & '\uffff');
               break;
            case oT:
               var6 = new bad(var0.getShort() & '\uffff', var0.getShort() & '\uffff');
               break;
            case sY:
               var6 = new bae(var0.getShort() & '\uffff');
               break;
            case pC:
               int var7 = var0.getShort() & '\uffff';
               if (var3.length < var7) {
                  var3 = new byte[var7];
               }

               var0.get(var3, 0, var7);
               var6 = new baf(var3, 0, var7);
               break;
            case fI:
               var6 = new baa(var0.get() & 255, var0.getShort() & '\uffff');
               break;
            case WR:
               var6 = new bab(var0.getShort() & '\uffff');
               break;
            case NS:
               var6 = new azy(var0.getShort() & '\uffff', var0.getShort() & '\uffff');
         }

         if (var6 == null) {
            throw new RuntimeException("Unsupported pool entry: " + bah.pC(var5));
         }

         ((azs)var6).pC = var4;
         var2.pC.add(var6);
         if (((azs)var6).pC() == bah.wS || ((azs)var6).pC() == bah.UT) {
            var2.pC.add(null);
            var4++;
         }
      }

      return var2;
   }
}
