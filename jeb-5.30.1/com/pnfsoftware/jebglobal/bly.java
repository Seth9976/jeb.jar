package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.ICodeField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaFieldFactory;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.util.DecompilerHelper;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

@Ser
public class bly implements IJavaFieldFactory {
   @SerId(1)
   private com.pnfsoftware.jeb.corei.parsers.dex.bK q;
   @SerId(2)
   private cis RF;
   @SerId(4)
   private bmv xK;
   @SerId(3)
   private blw Dw;
   @SerId(5)
   private Map Uv;
   @SerId(6)
   private com.pnfsoftware.jeb.corei.parsers.dexdec.ej oW;

   @SerCustomInitPostGraph
   private void oW() {
      if (this.Uv instanceof HashMap) {
         this.Uv = new ConcurrentHashMap(this.Uv);
      }

      if (this.oW == null) {
         this.oW = (com.pnfsoftware.jeb.corei.parsers.dexdec.ej)DecompilerHelper.getDecompiler(this.q, false);
         if (this.oW != null) {
            this.Uv.values().forEach(var1 -> blw.q(var1, this.oW));
         }
      }
   }

   public bly(com.pnfsoftware.jeb.corei.parsers.dexdec.ej var1, cis var2, bmv var3, blw var4) {
      this.oW = var1;
      this.q = var1.TX();
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
      this.Uv = new ConcurrentHashMap();
   }

   public bnn q(String var1) {
      bjo var2 = this.q.xK(var1);
      return var2 == null ? null : (bnn)this.Uv.get(var2.getIndex());
   }

   public bnn RF(String var1) {
      bjo var2 = this.q.xK(var1);
      return var2 == null ? null : this.q(var2.getIndex());
   }

   private bnn q(int var1) {
      if (var1 < 0 && var1 != -1 && var1 != -2) {
         throw new RuntimeException("Illegal field index");
      } else {
         bnn var2 = (bnn)this.Uv.get(var1);
         if (var2 != null) {
            return var2;
         } else {
            synchronized (this) {
               var2 = (bnn)this.Uv.get(var1);
               if (var2 != null) {
                  return var2;
               } else {
                  boolean var8 = true;
                  String var10 = null;
                  String var4;
                  Object var5;
                  IJavaType var6;
                  int var7;
                  if (var1 == -1) {
                     var4 = "class";
                     var5 = this.RF.parseType("Ljava/lang/Class;");
                     var6 = null;
                     var7 = 8;
                  } else if (var1 == -2) {
                     var4 = "length";
                     var5 = this.RF.oW;
                     var6 = null;
                     var7 = 0;
                  } else {
                     ICodeField var9 = (ICodeField)this.q.getFields().get(var1);
                     var4 = var9.getName(false);
                     var5 = this.RF.parseType(var9.getFieldType().getSignature(false));
                     var6 = this.RF.parseType(var9.getClassType().getSignature(false));
                     var7 = blw.q(var9.getGenericFlags());
                     var10 = var9.getSignature(false);
                     var8 = !var9.isInternal();
                     if (var8 && Booleans.isTrue(this.oW.RF(var10))) {
                        var7 |= 8;
                     }
                  }

                  var2 = new bnn(var1, var8, var10, var6, var4, (IJavaType)var5, var7);
                  var2.q(this.oW);
                  this.Uv.put(var1, var2);
                  return var2;
               }
            }
         }
      }
   }

   public void q() {
      this.Uv.clear();
   }

   public int RF() {
      return this.Uv.size();
   }

   public boolean xK(String var1) {
      bnn var2 = this.q(var1);
      return var2 != null && this.q(var2);
   }

   public boolean q(bnn var1) {
      int var2 = var1.xK();
      bnn var3 = (bnn)this.Uv.remove(var2);
      return var3 != null;
   }

   public int xK() {
      ArrayList var1 = new ArrayList();

      for (Entry var3 : this.Uv.entrySet()) {
         if ((((bnn)var3.getValue()).getFlags() & 256) == 0) {
            var1.add((Integer)var3.getKey());
         }
      }

      var1.forEach(var1x -> this.Uv.remove(var1x));
      return var1.size();
   }

   public Collection Dw() {
      return Collections.unmodifiableCollection(this.Uv.values());
   }

   public int Uv() {
      ArrayList var1 = new ArrayList();

      for (Entry var3 : this.Uv.entrySet()) {
         int var4 = (Integer)var3.getKey();
         bnn var5 = (bnn)var3.getValue();
         if (this.q.oW(var4).isInternal() && var5.isExternal()) {
            var1.add(var4);
         }
      }

      var1.forEach(var1x -> this.Uv.remove(var1x));
      return var1.size();
   }

   @Override
   public String toString() {
      return this.Uv.toString();
   }
}
