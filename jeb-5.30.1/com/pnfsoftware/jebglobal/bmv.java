package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaConstantFactory;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeFactory;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser
public class bmv implements IJavaConstantFactory {
   private static final ILogger q = GlobalLog.getLogger(bmv.class);
   @SerId(1)
   private IJavaTypeFactory RF;
   @SerId(2)
   private IJavaType xK;
   @SerId(3)
   private bmu Dw;
   @SerId(4)
   private Map Uv;
   @SerId(5)
   private Map oW;
   @SerId(6)
   private Map gO;
   @SerId(7)
   private Map nf;
   @SerId(8)
   private Map gP;
   @SerId(9)
   private Map za;
   @SerId(10)
   private Map lm;
   @SerId(11)
   private Map zz;
   @SerId(12)
   private Map JY;
   @SerId(13)
   private List HF;

   public bmv(IJavaTypeFactory var1) {
      this.RF = var1;
      this.xK = var1.getJavaLangString();
      this.HF = new ArrayList();
      this.Dw = this.q(new bmu());
      this.Uv = new HashMap();
      this.oW = new HashMap();
      this.gO = new HashMap();
      this.nf = new HashMap();
      this.gP = new HashMap();
      this.za = new HashMap();
      this.lm = new HashMap();
      this.zz = new HashMap();
      this.JY = new HashMap();
   }

   private bmu q(bmu var1) {
      if (this.HF != null) {
         var1.Dw = this.HF.size();
         this.HF.add(var1);
      }

      return var1;
   }

   public synchronized bmu q(int var1) {
      return this.HF != null && var1 >= 0 && var1 < this.HF.size() ? (bmu)this.HF.get(var1) : null;
   }

   public bmu q() {
      return this.Dw;
   }

   public synchronized bmu q(boolean var1, String var2) {
      if (var2 != null) {
         bmu var4 = this.q(new bmu(this.RF.getBoolean(), var1));
         var4.setOrigin(var2);
         return var4;
      } else {
         bmu var3 = (bmu)this.Uv.get(var1);
         if (var3 == null) {
            var3 = this.q(new bmu(this.RF.getBoolean(), var1));
            this.Uv.put(var1, var3);
         }

         return var3;
      }
   }

   public synchronized bmu q(byte var1, String var2) {
      if (var2 != null) {
         bmu var4 = this.q(new bmu(this.RF.getByte(), var1));
         var4.setOrigin(var2);
         return var4;
      } else {
         bmu var3 = (bmu)this.oW.get(var1);
         if (var3 == null) {
            var3 = this.q(new bmu(this.RF.getByte(), var1));
            this.oW.put(var1, var3);
         }

         return var3;
      }
   }

   public synchronized bmu q(char var1, String var2) {
      if (var2 != null) {
         bmu var4 = this.q(new bmu(this.RF.getChar(), var1));
         var4.setOrigin(var2);
         return var4;
      } else {
         bmu var3 = (bmu)this.gO.get(var1);
         if (var3 == null) {
            var3 = this.q(new bmu(this.RF.getChar(), var1));
            this.gO.put(var1, var3);
         }

         return var3;
      }
   }

   public synchronized bmu q(short var1, String var2) {
      if (var2 != null) {
         bmu var4 = this.q(new bmu(this.RF.getShort(), var1));
         var4.setOrigin(var2);
         return var4;
      } else {
         bmu var3 = (bmu)this.nf.get(var1);
         if (var3 == null) {
            var3 = this.q(new bmu(this.RF.getShort(), var1));
            this.nf.put(var1, var3);
         }

         return var3;
      }
   }

   public synchronized bmu q(int var1, String var2) {
      if (var2 != null) {
         bmu var4 = this.q(new bmu(this.RF.getInt(), var1));
         var4.setOrigin(var2);
         return var4;
      } else {
         bmu var3 = (bmu)this.gP.get(var1);
         if (var3 == null) {
            var3 = this.q(new bmu(this.RF.getInt(), var1));
            this.gP.put(var1, var3);
         }

         return var3;
      }
   }

   public synchronized bmu q(long var1, String var3) {
      if (var3 != null) {
         bmu var5 = this.q(new bmu(this.RF.getLong(), var1));
         var5.setOrigin(var3);
         return var5;
      } else {
         bmu var4 = (bmu)this.za.get(var1);
         if (var4 == null) {
            var4 = this.q(new bmu(this.RF.getLong(), var1));
            this.za.put(var1, var4);
         }

         return var4;
      }
   }

   public synchronized bmu q(float var1, String var2) {
      if (var2 != null) {
         bmu var4 = this.q(new bmu(this.RF.getFloat(), var1));
         var4.setOrigin(var2);
         return var4;
      } else {
         bmu var3 = (bmu)this.lm.get(var1);
         if (var3 == null) {
            var3 = this.q(new bmu(this.RF.getFloat(), var1));
            this.lm.put(var1, var3);
         }

         return var3;
      }
   }

   public synchronized bmu q(double var1, String var3) {
      if (var3 != null) {
         bmu var5 = this.q(new bmu(this.RF.getDouble(), var1));
         var5.setOrigin(var3);
         return var5;
      } else {
         bmu var4 = (bmu)this.zz.get(var1);
         if (var4 == null) {
            var4 = this.q(new bmu(this.RF.getDouble(), var1));
            this.zz.put(var1, var4);
         }

         return var4;
      }
   }

   public synchronized bmu q(String var1, String var2) {
      if (var1 == null) {
         Object[] var10000 = new Object[0];
         var2 = null;
      }

      if (var2 != null) {
         bmu var4 = this.q(new bmu(this.xK, var1));
         var4.setOrigin(var2);
         return var4;
      } else {
         bmu var3 = (bmu)this.JY.get(var1);
         if (var3 == null) {
            var3 = this.q(new bmu(this.xK, var1));
            this.JY.put(var1, var3);
         }

         return var3;
      }
   }
}
