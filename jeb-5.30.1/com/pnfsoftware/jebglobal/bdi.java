package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class bdi implements Iterable {
   private static final ILogger RF = GlobalLog.getLogger(bdi.class);
   List q;

   private bdi(int var1) {
      this.q = new ArrayList(var1);
   }

   @Override
   public Iterator iterator() {
      return this.q.iterator();
   }

   public int q() {
      return this.q.size();
   }

   public bdk q(int var1) {
      return var1 > 0 && var1 <= this.q.size() ? (bdk)this.q.get(var1 - 1) : null;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   static bdi q(ByteBuffer var0, int var1) {
      bdi var2 = new bdi(var1);
      byte[] var3 = new byte[0];

      for (int var4 = 1; var4 <= var1; var4++) {
         int var5 = var0.get() & 255;
         Object var6 = null;
         switch (bea.q(var5)) {
            case RF:
               var6 = new bdp(var0.getInt());
               break;
            case xK:
               var6 = new bdo(var0.getFloat());
               break;
            case Dw:
               var6 = new bds(var0.getLong());
               break;
            case Uv:
               var6 = new bdm(var0.getDouble());
               break;
            case oW:
               var6 = new bdl(var0.getShort() & '\uffff');
               break;
            case nf:
               var6 = new bdn(var0.getShort() & '\uffff', var0.getShort() & '\uffff');
               break;
            case gP:
               var6 = new bdv(var0.getShort() & '\uffff', var0.getShort() & '\uffff');
               break;
            case za:
               var6 = new bdn(var0.getShort() & '\uffff', var0.getShort() & '\uffff');
               break;
            case lm:
               var6 = new bdw(var0.getShort() & '\uffff', var0.getShort() & '\uffff');
               break;
            case gO:
               var6 = new bdx(var0.getShort() & '\uffff');
               break;
            case q:
               int var7 = var0.getShort() & '\uffff';
               if (var3.length < var7) {
                  var3 = new byte[var7];
               }

               var0.get(var3, 0, var7);
               var6 = new bdy(var3, 0, var7);
               break;
            case zz:
               var6 = new bdt(var0.get() & 255, var0.getShort() & '\uffff');
               break;
            case JY:
               var6 = new bdu(var0.getShort() & '\uffff');
               break;
            case HF:
               var6 = new bdr(var0.getShort() & '\uffff', var0.getShort() & '\uffff');
         }

         if (var6 == null) {
            throw new RuntimeException("Unsupported pool entry: " + bea.q(var5));
         }

         ((bdk)var6).q = var4;
         var2.q.add(var6);
         if (((bdk)var6).RF() == bea.Dw || ((bdk)var6).RF() == bea.Uv) {
            var2.q.add(null);
            var4++;
         }
      }

      return var2;
   }
}
