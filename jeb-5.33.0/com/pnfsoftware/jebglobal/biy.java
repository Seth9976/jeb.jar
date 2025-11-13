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
public class biy implements IJavaConstantFactory {
   private static final ILogger pC = GlobalLog.getLogger(biy.class);
   @SerId(1)
   private IJavaTypeFactory A;
   @SerId(2)
   private IJavaType kS;
   @SerId(3)
   private bix wS;
   @SerId(4)
   private Map UT;
   @SerId(5)
   private Map E;
   @SerId(6)
   private Map sY;
   @SerId(7)
   private Map ys;
   @SerId(8)
   private Map ld;
   @SerId(9)
   private Map gp;
   @SerId(10)
   private Map oT;
   @SerId(11)
   private Map fI;
   @SerId(12)
   private Map WR;
   @SerId(13)
   private List NS;

   public biy(IJavaTypeFactory var1) {
      this.A = var1;
      this.kS = var1.getJavaLangString();
      this.NS = new ArrayList();
      this.wS = this.pC(new bix());
      this.UT = new HashMap();
      this.E = new HashMap();
      this.sY = new HashMap();
      this.ys = new HashMap();
      this.ld = new HashMap();
      this.gp = new HashMap();
      this.oT = new HashMap();
      this.fI = new HashMap();
      this.WR = new HashMap();
   }

   private bix pC(bix var1) {
      if (this.NS != null) {
         var1.A = this.NS.size();
         this.NS.add(var1);
      }

      return var1;
   }

   public synchronized bix pC(int var1) {
      return this.NS != null && var1 >= 0 && var1 < this.NS.size() ? (bix)this.NS.get(var1) : null;
   }

   public bix pC() {
      return this.wS;
   }

   public synchronized bix pC(boolean var1, String var2) {
      if (var2 != null) {
         bix var4 = this.pC(new bix(this.A.getBoolean(), var1));
         var4.setOrigin(var2);
         return var4;
      } else {
         bix var3 = (bix)this.UT.get(var1);
         if (var3 == null) {
            var3 = this.pC(new bix(this.A.getBoolean(), var1));
            this.UT.put(var1, var3);
         }

         return var3;
      }
   }

   public synchronized bix pC(byte var1, String var2) {
      if (var2 != null) {
         bix var4 = this.pC(new bix(this.A.getByte(), var1));
         var4.setOrigin(var2);
         return var4;
      } else {
         bix var3 = (bix)this.E.get(var1);
         if (var3 == null) {
            var3 = this.pC(new bix(this.A.getByte(), var1));
            this.E.put(var1, var3);
         }

         return var3;
      }
   }

   public synchronized bix pC(char var1, String var2) {
      if (var2 != null) {
         bix var4 = this.pC(new bix(this.A.getChar(), var1));
         var4.setOrigin(var2);
         return var4;
      } else {
         bix var3 = (bix)this.sY.get(var1);
         if (var3 == null) {
            var3 = this.pC(new bix(this.A.getChar(), var1));
            this.sY.put(var1, var3);
         }

         return var3;
      }
   }

   public synchronized bix pC(short var1, String var2) {
      if (var2 != null) {
         bix var4 = this.pC(new bix(this.A.getShort(), var1));
         var4.setOrigin(var2);
         return var4;
      } else {
         bix var3 = (bix)this.ys.get(var1);
         if (var3 == null) {
            var3 = this.pC(new bix(this.A.getShort(), var1));
            this.ys.put(var1, var3);
         }

         return var3;
      }
   }

   public synchronized bix pC(int var1, String var2) {
      if (var2 != null) {
         bix var4 = this.pC(new bix(this.A.getInt(), var1));
         var4.setOrigin(var2);
         return var4;
      } else {
         bix var3 = (bix)this.ld.get(var1);
         if (var3 == null) {
            var3 = this.pC(new bix(this.A.getInt(), var1));
            this.ld.put(var1, var3);
         }

         return var3;
      }
   }

   public synchronized bix pC(long var1, String var3) {
      if (var3 != null) {
         bix var5 = this.pC(new bix(this.A.getLong(), var1));
         var5.setOrigin(var3);
         return var5;
      } else {
         bix var4 = (bix)this.gp.get(var1);
         if (var4 == null) {
            var4 = this.pC(new bix(this.A.getLong(), var1));
            this.gp.put(var1, var4);
         }

         return var4;
      }
   }

   public synchronized bix pC(float var1, String var2) {
      if (var2 != null) {
         bix var4 = this.pC(new bix(this.A.getFloat(), var1));
         var4.setOrigin(var2);
         return var4;
      } else {
         bix var3 = (bix)this.oT.get(var1);
         if (var3 == null) {
            var3 = this.pC(new bix(this.A.getFloat(), var1));
            this.oT.put(var1, var3);
         }

         return var3;
      }
   }

   public synchronized bix pC(double var1, String var3) {
      if (var3 != null) {
         bix var5 = this.pC(new bix(this.A.getDouble(), var1));
         var5.setOrigin(var3);
         return var5;
      } else {
         bix var4 = (bix)this.fI.get(var1);
         if (var4 == null) {
            var4 = this.pC(new bix(this.A.getDouble(), var1));
            this.fI.put(var1, var4);
         }

         return var4;
      }
   }

   public synchronized bix pC(String var1, String var2) {
      if (var1 == null) {
         Object[] var10000 = new Object[0];
         var2 = null;
      }

      if (var2 != null) {
         bix var4 = this.pC(new bix(this.kS, var1));
         var4.setOrigin(var2);
         return var4;
      } else {
         bix var3 = (bix)this.WR.get(var1);
         if (var3 == null) {
            var3 = this.pC(new bix(this.kS, var1));
            this.WR.put(var1, var3);
         }

         return var3;
      }
   }
}
