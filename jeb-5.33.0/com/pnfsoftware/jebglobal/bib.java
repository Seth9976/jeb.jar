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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

@Ser
public class bib implements IJavaFieldFactory {
   @SerId(1)
   private com.pnfsoftware.jeb.corei.parsers.dex.vi pC;
   @SerId(2)
   private cdk A;
   @SerId(4)
   private biy kS;
   @SerId(3)
   private bhz wS;
   @SerId(5)
   private Map UT;
   @SerId(6)
   private com.pnfsoftware.jeb.corei.parsers.dexdec.Ws E;

   @SerCustomInitPostGraph
   private void kS() {
      if (this.UT instanceof HashMap) {
         this.UT = new ConcurrentHashMap(this.UT);
      }

      if (this.E == null) {
         this.E = (com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)DecompilerHelper.getDecompiler(this.pC, false);
         if (this.E != null) {
            this.UT.values().forEach(var1 -> bhz.pC(var1, this.E));
         }
      }
   }

   public bib(com.pnfsoftware.jeb.corei.parsers.dexdec.Ws var1, cdk var2, biy var3, bhz var4) {
      this.E = var1;
      this.pC = var1.ld();
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
      this.UT = new ConcurrentHashMap();
   }

   public bjq pC(String var1) {
      bft var2 = this.pC.kS(var1);
      return var2 == null ? null : (bjq)this.UT.get(var2.getIndex());
   }

   public bjq A(String var1) {
      bft var2 = this.pC.kS(var1);
      return var2 == null ? null : this.pC(var2.getIndex());
   }

   private bjq pC(int var1) {
      if (var1 < 0 && var1 != -1 && var1 != -2) {
         throw new RuntimeException("Illegal field index");
      } else {
         bjq var2 = (bjq)this.UT.get(var1);
         if (var2 != null) {
            return var2;
         } else {
            synchronized (this) {
               var2 = (bjq)this.UT.get(var1);
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
                     var5 = this.A.parseType("Ljava/lang/Class;");
                     var6 = null;
                     var7 = 8;
                  } else if (var1 == -2) {
                     var4 = "length";
                     var5 = this.A.E;
                     var6 = null;
                     var7 = 0;
                  } else {
                     ICodeField var9 = (ICodeField)this.pC.getFields().get(var1);
                     var4 = var9.getName(false);
                     var5 = this.A.parseType(var9.getFieldType().getSignature(false));
                     var6 = this.A.parseType(var9.getClassType().getSignature(false));
                     var7 = bhz.pC(var9.getGenericFlags());
                     var10 = var9.getSignature(false);
                     var8 = !var9.isInternal();
                     if (var8 && Booleans.isTrue(this.E.A(var10))) {
                        var7 |= 8;
                     }
                  }

                  var2 = new bjq(var1, var8, var10, var6, var4, (IJavaType)var5, var7);
                  var2.pC(this.E);
                  this.UT.put(var1, var2);
                  return var2;
               }
            }
         }
      }
   }

   public boolean kS(String var1) {
      bjq var2 = this.pC(var1);
      return var2 != null && this.pC(var2);
   }

   public boolean pC(bjq var1) {
      int var2 = var1.A();
      bjq var3 = (bjq)this.UT.remove(var2);
      return var3 != null;
   }

   public int pC() {
      ArrayList var1 = new ArrayList();

      for (Entry var3 : this.UT.entrySet()) {
         if ((((bjq)var3.getValue()).getFlags() & 256) == 0) {
            var1.add((Integer)var3.getKey());
         }
      }

      var1.forEach(var1x -> this.UT.remove(var1x));
      return var1.size();
   }

   public int A() {
      ArrayList var1 = new ArrayList();

      for (Entry var3 : this.UT.entrySet()) {
         int var4 = (Integer)var3.getKey();
         bjq var5 = (bjq)var3.getValue();
         if (this.pC.E(var4).isInternal() && var5.isExternal()) {
            var1.add(var4);
         }
      }

      var1.forEach(var1x -> this.UT.remove(var1x));
      return var1.size();
   }

   @Override
   public String toString() {
      return this.UT.toString();
   }
}
