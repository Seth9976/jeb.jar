package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.units.code.ICodeUnit;
import com.pnfsoftware.jeb.core.units.code.debug.ITypedValue;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueObject;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.Nv;
import com.pnfsoftware.jebglobal.bA;
import com.pnfsoftware.jebglobal.bft;
import com.pnfsoftware.jebglobal.bfy;
import com.pnfsoftware.jebglobal.oY;
import com.pnfsoftware.jebglobal.rG;
import com.pnfsoftware.jebglobal.sK;
import com.pnfsoftware.jebglobal.wX;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class gb extends ValueObject {
   private static final ILogger pC = GlobalLog.getLogger(gb.class);
   Tb UT;
   private List A = null;

   public gb(Tb var1, long var2, long var4, String var6) {
      super(var2, var4, var6);
      this.UT = var1;
   }

   @Override
   public long getRefTypeId() {
      if (this.refTypeId == 0L && this.getObjectId() != 0L) {
         try {
            bA var1 = (bA)this.UT.UT();
            sK var2 = var1.gp().E(this.getObjectId());
            this.refTypeId = var2.A;
         } catch (IOException | oY var3) {
            pC.catching(var3);
         }
      }

      return this.refTypeId;
   }

   @Override
   public String getTypeName() {
      String var1 = this.pC();
      if (var1 == null) {
         return super.getTypeName();
      } else {
         for (ICodeUnit var3 : this.UT.getPotentialDebuggees()) {
            if (var3.isProcessed()) {
               bfy var4 = ((com.pnfsoftware.jeb.corei.parsers.dex.vi)var3).ld().sY(var1);
               if (var4 != null) {
                  return var4.getSignature(true);
               }
            }
         }

         return var1;
      }
   }

   public String pC() {
      if (this.getObjectId() == 0L) {
         return null;
      } else {
         try {
            if (!this.UT.pC()) {
               return null;
            } else {
               bA var1 = (bA)this.UT.UT();
               long var2 = this.getRefTypeId();
               return var1.oT().kS(var2);
            }
         } catch (wX | IOException var4) {
            pC.catching(var4);
            return null;
         }
      }
   }

   @Override
   public boolean hasChildren() {
      if (this.getObjectId() == 0L) {
         return false;
      } else if (!this.UT.pC()) {
         return false;
      } else {
         try {
            bA var1 = (bA)this.UT.UT();
            long var2 = this.getRefTypeId();
            Nv[] var4 = var1.oT().pC(var2);
            return var4.length > 0;
         } catch (wX | IOException var5) {
            pC.catching(var5);
            return false;
         }
      }
   }

   @Override
   public synchronized List getValue() {
      if (this.A != null) {
         return this.A;
      } else {
         long var1 = this.getObjectId();
         if (var1 == 0L) {
            return null;
         } else {
            this.A = new ArrayList();

            try {
               if (!this.UT.pC()) {
                  return null;
               }

               bA var3 = (bA)this.UT.UT();
               long var4 = this.getRefTypeId();
               long var6 = var4;
               Object[] var10000 = new Object[]{var1, var1};

               for (int var8 = 0; var6 != 0L; var6 = var3.oT().sY(var6)) {
                  if (var8++ >= 50) {
                     pC.error("Object structure is too deep");
                     return null;
                  }

                  String var9 = var3.oT().kS(var6);
                  var10000 = new Object[]{var9};
                  Nv[] var10 = var3.oT().pC(var6);
                  HashMap var11 = new HashMap();
                  ArrayList var12 = new ArrayList();
                  ArrayList var13 = new ArrayList();

                  for (Nv var17 : var10) {
                     long var18 = var17.pC;
                     var11.put(var18, var17);
                     if ((var17.UT & 8) != 0) {
                        var13.add(var18);
                     } else {
                        var12.add(var18);
                     }
                  }

                  List var21 = var3.gp().A(var1, var12);
                  this.pC(var1, var21, var12, var11, var9, this.A);
                  List var22 = var3.gp().pC(var6, var13);
                  this.pC(var6, var22, var13, var11, var9, this.A);
               }
            } catch (wX | oY | IOException var20) {
               pC.catching(var20);
               return null;
            }

            return this.A;
         }
      }
   }

   private void pC(long var1, List var3, List var4, Map var5, String var6, List var7) throws IOException, wX {
      int var8 = 0;

      for (rG var10 : var3) {
         Nv var11 = (Nv)var5.get(var4.get(var8));
         ITypedValue var12 = this.UT.pC(var10, var11.kS);
         if (var12 != null) {
            String var13 = var11.A;
            String var14 = var6 + "->" + var13 + ":" + var11.kS;

            for (ICodeUnit var16 : this.UT.getPotentialDebuggees()) {
               bft var17 = ((com.pnfsoftware.jeb.corei.parsers.dex.vi)var16).kS(var14);
               if (var17 != null) {
                  var13 = var17.getName(true);
                  break;
               }
            }

            var7.add(new Kr(var13, var12, pC(var11.UT), this.UT, new Kr.Av(var1, var11.pC)));
         }

         var8++;
      }
   }

   public static int pC(int var0) {
      byte var1 = 0;
      if ((var0 & 1) != 0) {
         var1 |= 1;
      } else if ((var0 & 2) != 0) {
         var1 |= 2;
      } else if ((var0 & 4) != 0) {
         var1 |= 4;
      }

      if ((var0 & 8) != 0) {
         var1 |= 8;
      }

      if ((var0 & 16) != 0) {
         var1 |= 16;
      }

      return var1;
   }

   @Override
   public String format() {
      if (this.getObjectId() == 0L) {
         return "null";
      } else {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "object@%d (type=%s)\n", this.getObjectId(), this.getTypeName());
         List var2 = this.getValue();
         if (var2 != null) {
            for (Kr var4 : var2) {
               Strings.ff(var1, "- %s\n", var4);
            }
         }

         return var1.toString();
      }
   }

   @Override
   public String toString() {
      return this.getObjectId() == 0L ? "null" : Strings.ff("object@%d", this.getObjectId());
   }

   @Override
   public ITypedValue invoke(String var1, long var2, List var4) throws wX {
      try {
         bA var5 = (bA)this.UT.UT();
         long var6 = this.getRefTypeId();
         rG var8 = var5.oT().pC(var2, this.getObjectId(), var6, var1, this.UT.A(var4));
         return this.UT.pC(var8);
      } catch (IOException var9) {
         pC.catching(var9);
         return null;
      }
   }
}
