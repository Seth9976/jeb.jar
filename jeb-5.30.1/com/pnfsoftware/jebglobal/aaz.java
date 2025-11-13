package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.DataGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.NativeAnalyzerException;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Ser
public class aaz {
   @SerId(1)
   private final MultiMap q;
   @SerId(2)
   private final int RF;

   public aaz(MultiMap var1) {
      this.q = var1;
      this.RF = (Integer)Collections.max(var1.keySet());
   }

   public List q() {
      ArrayList var1 = new ArrayList();

      for (aao var3 : this.q.values()) {
         List var4 = var3.Dw();
         if (var4 != null) {
            var1.addAll(var4);
         }
      }

      return var1;
   }

   public DataGapAnalysisStyle RF() {
      HashSet var1 = new HashSet();

      for (int var2 = this.RF; var2 >= 0; var2--) {
         for (aao var4 : this.q.get(var2)) {
            DataGapAnalysisStyle var5 = var4.Uv();
            if (var5 != null) {
               var1.add(var5);
            }
         }

         if (!var1.isEmpty()) {
            break;
         }
      }

      if (var1.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var1.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (DataGapAnalysisStyle)var1.iterator().next();
      }
   }

   public CodeGapAnalysisStyle xK() {
      HashSet var1 = new HashSet();

      for (int var2 = this.RF; var2 >= 0; var2--) {
         for (aao var4 : this.q.get(var2)) {
            CodeGapAnalysisStyle var5 = var4.oW();
            if (var5 != null) {
               var1.add(var5);
            }
         }

         if (!var1.isEmpty()) {
            break;
         }
      }

      if (var1.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var1.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (CodeGapAnalysisStyle)var1.iterator().next();
      }
   }

   public aap.eo q(INativeCodeUnit var1) {
      HashSet var2 = new HashSet();

      for (int var3 = this.RF; var3 >= 0; var3--) {
         for (aao var5 : this.q.get(var3)) {
            aap.eo var6 = var5.RF(var1);
            if (var6 != null) {
               var2.add(var6);
            }
         }

         if (!var2.isEmpty()) {
            break;
         }
      }

      if (var2.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var2.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (aap.eo)var2.iterator().next();
      }
   }

   public ctk.eo RF(INativeCodeUnit var1) {
      HashSet var2 = new HashSet();

      for (int var3 = this.RF; var3 >= 0; var3--) {
         for (aao var5 : this.q.get(var3)) {
            ctk.eo var6 = var5.xK(var1);
            if (var6 != null) {
               var2.add(var6);
            }
         }

         if (!var2.isEmpty()) {
            break;
         }
      }

      if (var2.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var2.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (ctk.eo)var2.iterator().next();
      }
   }

   public Boolean Dw() {
      HashSet var1 = new HashSet();

      for (int var2 = this.RF; var2 >= 0; var2--) {
         for (aao var4 : this.q.get(var2)) {
            Boolean var5 = var4.gO();
            if (var5 != null) {
               var1.add(var5);
            }
         }

         if (!var1.isEmpty()) {
            break;
         }
      }

      if (var1.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var1.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (Boolean)var1.iterator().next();
      }
   }

   public Boolean Uv() {
      HashSet var1 = new HashSet();

      for (int var2 = this.RF; var2 >= 0; var2--) {
         for (aao var4 : this.q.get(var2)) {
            Boolean var5 = var4.nf();
            if (var5 != null) {
               var1.add(var5);
            }
         }

         if (!var1.isEmpty()) {
            break;
         }
      }

      if (var1.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var1.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (Boolean)var1.iterator().next();
      }
   }

   public Integer oW() {
      HashSet var1 = new HashSet();

      for (int var2 = this.RF; var2 >= 0; var2--) {
         for (aao var4 : this.q.get(var2)) {
            Integer var5 = var4.gP();
            if (var5 != null) {
               var1.add(var5);
            }
         }

         if (!var1.isEmpty()) {
            break;
         }
      }

      if (var1.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var1.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (Integer)var1.iterator().next();
      }
   }

   public Boolean gO() {
      HashSet var1 = new HashSet();

      for (int var2 = this.RF; var2 >= 0; var2--) {
         for (aao var4 : this.q.get(var2)) {
            Boolean var5 = var4.za();
            if (var5 != null) {
               var1.add(var5);
            }
         }

         if (!var1.isEmpty()) {
            break;
         }
      }

      if (var1.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var1.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (Boolean)var1.iterator().next();
      }
   }

   public Integer nf() {
      HashSet var1 = new HashSet();

      for (int var2 = this.RF; var2 >= 0; var2--) {
         for (aao var4 : this.q.get(var2)) {
            Integer var5 = var4.lm();
            if (var5 != null) {
               var1.add(var5);
            }
         }

         if (!var1.isEmpty()) {
            break;
         }
      }

      if (var1.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var1.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (Integer)var1.iterator().next();
      }
   }

   public Integer gP() {
      HashSet var1 = new HashSet();

      for (int var2 = this.RF; var2 >= 0; var2--) {
         for (aao var4 : this.q.get(var2)) {
            Integer var5 = var4.zz();
            if (var5 != null) {
               var1.add(var5);
            }
         }

         if (!var1.isEmpty()) {
            break;
         }
      }

      if (var1.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var1.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (Integer)var1.iterator().next();
      }
   }

   public Integer za() {
      HashSet var1 = new HashSet();

      for (int var2 = this.RF; var2 >= 0; var2--) {
         for (aao var4 : this.q.get(var2)) {
            Integer var5 = var4.JY();
            if (var5 != null) {
               var1.add(var5);
            }
         }

         if (!var1.isEmpty()) {
            break;
         }
      }

      if (var1.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var1.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (Integer)var1.iterator().next();
      }
   }

   public MultiMap lm() {
      return this.q;
   }
}
