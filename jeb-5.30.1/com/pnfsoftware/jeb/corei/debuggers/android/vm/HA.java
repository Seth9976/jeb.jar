package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.units.code.ICodeUnit;
import com.pnfsoftware.jeb.core.units.code.debug.ITypedValue;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueObject;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.Fx;
import com.pnfsoftware.jebglobal.Ux;
import com.pnfsoftware.jebglobal.bjo;
import com.pnfsoftware.jebglobal.bjt;
import com.pnfsoftware.jebglobal.ch;
import com.pnfsoftware.jebglobal.gg;
import com.pnfsoftware.jebglobal.hw;
import com.pnfsoftware.jebglobal.zy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HA extends ValueObject {
   private static final ILogger q = GlobalLog.getLogger(HA.class);
   private static final int RF = 50;
   CI Uv;
   private List xK = null;

   public HA(CI var1, long var2, long var4, String var6) {
      super(var2, var4, var6);
      this.Uv = var1;
   }

   @Override
   public long getRefTypeId() {
      if (this.refTypeId == 0L && this.getObjectId() != 0L) {
         try {
            Ux var1 = (Ux)this.Uv.oW();
            gg var2 = var1.zz().gO(this.getObjectId());
            this.refTypeId = var2.RF;
         } catch (IOException | Fx var3) {
            q.catching(var3);
         }
      }

      return this.refTypeId;
   }

   @Override
   public String getTypeName() {
      String var1 = this.RF();
      if (var1 == null) {
         return super.getTypeName();
      } else {
         for (ICodeUnit var3 : this.Uv.getPotentialDebuggees()) {
            if (var3.isProcessed()) {
               bjt var4 = ((com.pnfsoftware.jeb.corei.parsers.dex.bK)var3).io().gO(var1);
               if (var4 != null) {
                  return var4.getSignature(true);
               }
            }
         }

         return var1;
      }
   }

   public String RF() {
      if (this.getObjectId() == 0L) {
         return null;
      } else {
         try {
            if (!this.Uv.RF()) {
               return null;
            } else {
               Ux var1 = (Ux)this.Uv.oW();
               long var2 = this.getRefTypeId();
               return var1.JY().oW(var2);
            }
         } catch (zy | IOException var4) {
            q.catching(var4);
            return null;
         }
      }
   }

   @Override
   public boolean hasChildren() {
      if (this.getObjectId() == 0L) {
         return false;
      } else if (!this.Uv.RF()) {
         return false;
      } else {
         try {
            Ux var1 = (Ux)this.Uv.oW();
            long var2 = this.getRefTypeId();
            hw[] var4 = var1.JY().q(var2);
            return var4.length > 0;
         } catch (zy | IOException var5) {
            q.catching(var5);
            return false;
         }
      }
   }

   @Override
   public synchronized List getValue() {
      if (this.xK != null) {
         return this.xK;
      } else {
         long var1 = this.getObjectId();
         if (var1 == 0L) {
            return null;
         } else {
            this.xK = new ArrayList();

            try {
               if (!this.Uv.RF()) {
                  return null;
               }

               Ux var3 = (Ux)this.Uv.oW();
               long var4 = this.getRefTypeId();
               long var6 = var4;
               Object[] var10000 = new Object[]{var1, var1};

               for (int var8 = 0; var6 != 0L; var6 = var3.JY().za(var6)) {
                  if (var8++ >= 50) {
                     q.error("Object structure is too deep");
                     return null;
                  }

                  String var9 = var3.JY().oW(var6);
                  var10000 = new Object[]{var9};
                  hw[] var10 = var3.JY().q(var6);
                  HashMap var11 = new HashMap();
                  ArrayList var12 = new ArrayList();
                  ArrayList var13 = new ArrayList();

                  for (hw var17 : var10) {
                     long var18 = var17.za;
                     var11.put(var18, var17);
                     if ((var17.HF & 8) != 0) {
                        var13.add(var18);
                     } else {
                        var12.add(var18);
                     }
                  }

                  List var21 = var3.zz().RF(var1, var12);
                  this.q(var1, var21, var12, var11, var9, this.xK);
                  List var22 = var3.zz().q(var6, var13);
                  this.q(var6, var22, var13, var11, var9, this.xK);
               }
            } catch (zy | Fx | IOException var20) {
               q.catching(var20);
               return null;
            }

            return this.xK;
         }
      }
   }

   private void q(long var1, List var3, List var4, Map var5, String var6, List var7) throws IOException, zy {
      int var8 = 0;

      for (ch var10 : var3) {
         hw var11 = (hw)var5.get(var4.get(var8));
         ITypedValue var12 = this.Uv.q(var10, var11.zz);
         if (var12 != null) {
            String var13 = var11.lm;
            String var14 = var6 + "->" + var13 + ":" + var11.zz;

            for (ICodeUnit var16 : this.Uv.getPotentialDebuggees()) {
               bjo var17 = ((com.pnfsoftware.jeb.corei.parsers.dex.bK)var16).xK(var14);
               if (var17 != null) {
                  var13 = var17.getName(true);
                  break;
               }
            }

            var7.add(new LR(var13, var12, q(var11.HF), this.Uv, new LR.eo(var1, var11.za)));
         }

         var8++;
      }
   }

   public static int q(int var0) {
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
            for (LR var4 : var2) {
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
   public ITypedValue invoke(String var1, long var2, List var4) throws zy {
      try {
         Ux var5 = (Ux)this.Uv.oW();
         long var6 = this.getRefTypeId();
         ch var8 = var5.JY().q(var2, this.getObjectId(), var6, var1, this.Uv.RF(var4));
         return this.Uv.q(var8);
      } catch (IOException var9) {
         q.catching(var9);
         return null;
      }
   }
}
