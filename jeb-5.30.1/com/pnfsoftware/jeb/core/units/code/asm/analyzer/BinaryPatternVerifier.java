package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Ser
public class BinaryPatternVerifier {
   @SerId(1)
   private List patterns = new ArrayList();
   @SerId(2)
   private int longestSize;

   public void addPatterns(Collection var1) {
      for (IBinaryPattern var3 : var1) {
         this.addPattern(var3);
      }
   }

   public void addPatterns(IBinaryPattern... var1) {
      for (IBinaryPattern var5 : var1) {
         this.addPattern(var5);
      }
   }

   public void addPattern(IBinaryPattern var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.patterns.add(var1);
         int var2 = var1.getBinary().length + var1.getRealStartOffset();
         if (var2 > this.longestSize) {
            this.longestSize = var2;
         }
      }
   }

   public Collection getPatterns() {
      return Collections.unmodifiableCollection(this.patterns);
   }

   public void clear() {
      this.patterns.clear();
      this.longestSize = 0;
   }

   public int getLongestSize() {
      return this.longestSize;
   }

   public IBinaryPattern verify(INativeCodeAnalyzer var1, long var2, byte[] var4, int var5, int var6) {
      return this.verify(var1, var2, var4, var5, var6, 0, 0);
   }

   public IBinaryPattern verify(INativeCodeAnalyzer var1, long var2, byte[] var4, int var5, int var6, int var7) {
      return this.verify(var1, var2, var4, var5, var6, var7, 0);
   }

   public IBinaryPattern verify(INativeCodeAnalyzer var1, long var2, byte[] var4, int var5, int var6, int var7, int var8) {
      if (var4 == null) {
         throw new NullPointerException();
      } else if (var5 >= 0 && var6 >= var5) {
         for (IBinaryPattern var10 : this.patterns) {
            if ((var8 == 0 || var10.getProcessorMode() == 0 || var8 == var10.getProcessorMode())
               && (var7 == 0 || var10.getBinary() == null || var10.getBinary().length == var7)
               && this.check(var1, var2, var10, var4, var5, var6)) {
               return var10;
            }
         }

         return null;
      } else {
         throw new IllegalArgumentException();
      }
   }

   private boolean check(INativeCodeAnalyzer var1, long var2, IBinaryPattern var4, byte[] var5, int var6, int var7) {
      byte[] var8 = var4.getBinary();
      int var9 = var6 + var4.getRealStartOffset();
      int var10 = var9 + var8.length;
      if (var10 > var7) {
         return false;
      } else {
         byte[] var11 = var4.getMask();
         if (var11 == null) {
            for (int var12 = 0; var12 < var8.length; var12++) {
               if (var5[var9 + var12] != var8[var12]) {
                  return false;
               }
            }
         } else {
            for (int var13 = 0; var13 < var8.length; var13++) {
               if ((var5[var9 + var13] & var11[var13]) != var8[var13]) {
                  return false;
               }
            }
         }

         return var4.validate(var1, var2, var5, var6, var7);
      }
   }
}
