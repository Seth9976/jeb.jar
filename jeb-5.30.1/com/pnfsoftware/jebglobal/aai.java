package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeStringItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.IArrayType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrimitiveType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Charsets;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Characters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class aai {
   private static final ILogger q = GlobalLog.getLogger(aai.class);
   private static final int RF = 8;
   private static final int xK = 4;
   private static final int Dw = 4;
   private static final int Uv = 3;
   private static final int oW = 2000;
   private final INativeCodeAnalyzer gO;
   private final aaj nf;
   private final ITypeManager gP;

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   private static int q(INativeCodeAnalyzer var0, INativeContinuousItem var1, int var2, long var3, int var5, int var6) {
      if (var1 instanceof axw var7) {
         String var8 = var7.getValue();

         for (int var9 = 0; var9 < var8.length(); var9++) {
            if (!Characters.isAsciiCharOrCommonFormat(var8.charAt(var9))) {
               return -1;
            }
         }

         int var14;
         int var16;
         try {
            var14 = (int)var1.getMemorySize();
            int var10 = 0;

            for (int var11 = var14; var11 != 0; var11 >>>= 1) {
               var10++;
            }

            var16 = 1 << var10;
            var16 = Math.max(var16, var6);
            if (var2 + var16 >= var5) {
               return -1;
            }
         } catch (MemoryException var13) {
            return -1;
         }

         while (true) {
            try {
               if (var14 > var16 || var2 + var14 >= var5) {
                  return var16;
               }

               if (var0.getMemory().readByte(var3 + var2 + var14) != 0) {
                  return -1;
               }
            } catch (MemoryException var12) {
               break;
            }

            var14++;
         }
      }

      return -1;
   }

   public aai(INativeCodeAnalyzer var1, aaj var2) {
      this.gO = var1;
      this.nf = var2;
      this.gP = var1.getTypeManager();
   }

   INativeDataItem q(long var1, int var3, String var4, int var5, aai.oM var6) {
      if (aak.q(var4)) {
         return null;
      } else if (!var6.RF && var6.gP.size() <= 1 && (var6.gP.size() != 1 || (Long)var6.gP.firstKey() == var1)) {
         boolean var7 = this.q(var6.lm, var1);
         if ((var6.xK.size() + var6.oW.size()) * var5 != var3
            && var3 % var5 == 0
            && var6.JY.size() == 1
            && ((aai.ej)var6.JY.iterator().next()).q() == var5
            && var6.Uv.isEmpty()) {
            boolean var8 = true;

            for (int var9 = 0; var9 < var3; var9 += var5) {
               if (!var6.xK.contains(var1 + var9) && !var6.oW.contains(var1 + var9)) {
                  Long var10 = VirtualMemoryUtil.readAsLongSafe(this.gO.getMemory(), var1 + var9, var5);
                  if (var10 == null || !this.gO.getAnalysisRanges().contains(var10) || aaj.xK(this.gO, var10)) {
                     var8 = false;
                     break;
                  }

                  INativeContinuousItem var11 = this.gO.getModel().getItemOver(var10);
                  if (var11 != null && (var11 instanceof INativeInstructionItem || var11.getMemoryAddress() != var10 && !(var11 instanceof INativeStringItem))) {
                     var8 = false;
                     break;
                  }
               }
            }

            if (var8) {
               for (int var20 = 0; var20 < var3; var20 += var5) {
                  if (!var6.oW.contains(var1 + var20)) {
                     var6.xK.add(var1 + var20);
                  }
               }
            }
         }

         if (!var6.xK.isEmpty()) {
            if (var6.xK.size() > var6.oW.size() && (var6.xK.size() + var6.oW.size()) * var5 == var3) {
               if (var7) {
                  IReferenceType var19 = this.gP.getVoidReference();
                  if (var19 != null) {
                     IArrayType var22 = this.gP.createArray(var19, var6.xK.size() + var6.oW.size());
                     return this.gO.defineData(var1, var22);
                  }
               } else {
                  var6.gO = true;
               }
            }

            return null;
         } else {
            boolean var18 = var6.xK.isEmpty() && var6.za.isEmpty();
            boolean var21 = this.q(var1, var3, var6.JY, var6.lm, var18, -1);
            if (var6.JY.size() == 0 && var7) {
               if (var3 % var5 == 0) {
                  var6.q(var5);
               } else if (var3 % (var5 >>> 1) == 0) {
                  var6.q(var5 >>> 1);
               }
            }

            if (var6.JY.size() == 1 && (var21 || var18)) {
               aai.ej var23 = (aai.ej)var6.JY.iterator().next();
               int var25 = var23.q();
               if (var3 % var23.q() == 0) {
                  if (var23 instanceof aai.nI) {
                     INativeType var14 = this.gP.getExactInteger(var25, false);
                     if (var14 != null) {
                        IArrayType var15 = this.gP.createArray(var14, var3 / var25);
                        var15.setFlags(4096);
                        return this.gO.defineData(var1, var15);
                     }
                  } else {
                     if (var23 instanceof aai.eo var12) {
                        INativeType var28 = this.gP.getExactInteger(var12.q, false);
                        IArrayType var31 = this.gP.createArray(var28, var12.RF);
                        IArrayType var33 = this.gP.createArray(var31, var3 / var25);
                        var33.setFlags(4096);
                        return this.gO.defineData(var1, var33);
                     }

                     if (var23 instanceof aai.CU var13) {
                        ArrayList var27 = new ArrayList();

                        for (INativeType var16 : var13.q) {
                           var27.add(var16.getName());
                        }

                        String var30 = "AUTO_GENERATED_";
                        INativeType var32 = this.q(var30, var13.q, var27);
                        IArrayType var17 = this.gP.createArray(var32, var3 / var25);
                        var17.setFlags(4096);
                        return this.gO.defineData(var1, var17);
                     }
                  }
               }
            }

            if (var21 || var3 % 2 == 0 && (var3 <= var5 || var3 % 4 == 0)) {
               return null;
            } else {
               INativeType var24 = this.gP.getExactInteger(1, false);
               IArrayType var26 = this.gP.createArray(var24, var3);
               var26.setFlags(4096);
               return this.gO.defineData(var1, var26);
            }
         }
      } else {
         return null;
      }
   }

   private boolean q(TreeMap var1, long var2) {
      if (var1.isEmpty()) {
         return true;
      } else if (var1.size() == 1 && (Long)var1.firstKey() == var2) {
         return true;
      } else if ((Long)var1.firstKey() != var2) {
         return false;
      } else {
         Set var4 = (Set)((Set)var1.firstEntry().getValue())
            .stream()
            .filter(var0 -> var0.getFrom().isInternalAddress())
            .map(var0 -> var0.getFrom().getInternalAddress())
            .collect(Collectors.toSet());

         for (Entry var6 : var1.entrySet()) {
            if ((Long)var6.getKey() != var2) {
               Stream var7 = ((Set)var6.getValue()).stream().filter(var1x -> !var4.contains(var1x.getFrom().getInternalAddress()));
               if (var7.count() != 0L) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   private boolean q(long var1, int var3, Set var4, TreeMap var5, boolean var6, int var7) {
      return q(this.gO.getMemory(), var1, var3, var4, var5, var6, var7);
   }

   public static boolean q(IVirtualMemory var0, long var1, int var3, Set var4, TreeMap var5, boolean var6, int var7) {
      long var8 = var1;
      int var10 = var3;
      boolean var11 = var7 >= 0;
      aai.iZ var12 = new aai.iZ(aai.Nt.q, 1, 8, var4);
      Boolean var13 = var12.q(var0, var1, var3, var7);
      if (var13 != null) {
         return var13;
      } else {
         boolean var14 = q(var1, var3, var5);
         if (var14) {
            var12 = new aai.iZ(aai.Nt.q, 2, 4, var4);
            if (var11) {
               var8 = var1 + var7 - (var12.xK - 1) * var12.Dw;
               var10 = Math.min((var12.xK - 1) * 2 * var12.Dw + (var0.getSpaceBits() >>> 3), var3 - var7);
            }

            aai.iZ var15 = new aai.iZ(aai.Nt.q, 2, 4, var4);
            aai.iZ var16 = new aai.iZ(aai.Nt.q, 2, 4, var4);

            for (int var17 = 0; var17 * var12.Dw < var10; var17++) {
               Long var18 = VirtualMemoryUtil.readAsLongSafe(var0, var8 + var17 * var12.Dw, var12.Dw);
               if (var18 == null) {
                  return false;
               }

               if (var12.q(var17, var18.intValue())) {
                  return true;
               }

               Integer var19 = q(var0, var18.intValue(), 10);
               if (var19 == null) {
                  var15.q[0].q(var17);
               } else if (var15.q(var17, var19.intValue())) {
                  return true;
               }

               Integer var20 = q(var0, var18.intValue(), 16);
               if (var20 == null) {
                  var16.q[0].q(var17);
               } else if (var16.q(var17, var20.intValue())) {
                  return true;
               }
            }
         }

         boolean var38 = RF(var1, var3, var5);
         if (var38) {
            var12 = new aai.iZ(aai.Nt.q, 4, 4, var4);
            var13 = var12.q(var0, var1, var3, var7);
            if (var13 != null) {
               return var13;
            }
         }

         boolean var39 = xK(var1, var3, var5);
         if (var39) {
            var12 = new aai.iZ(aai.Nt.q, 4, 2, 4, var4);
            var13 = var12.q(var0, var1, var3, var7);
            if (var13 != null) {
               return var13;
            }

            var12 = new aai.iZ(aai.Nt.q, 8, 4, var4);
            var13 = var12.q(var0, var1, var3, var7);
            if (var13 != null) {
               return var13;
            }
         }

         var12 = new aai.iZ(aai.Nt.xK, 1, 3, var4);
         var13 = var12.q(var0, var1, var3, var7);
         if (var13 != null) {
            return var13;
         } else {
            if (var14) {
               var12 = new aai.iZ(aai.Nt.xK, 2, 3, var4);
               var13 = var12.q(var0, var1, var3, var7);
               if (var13 != null) {
                  return var13;
               }
            }

            if (var4.contains(aai.nI.q) && var4.contains(aai.nI.RF)) {
               var4.remove(aai.nI.RF);
            }

            if (var6 && !var11) {
               if (var38) {
                  var12 = new aai.iZ(aai.Nt.xK, 4, 3, var4);
                  var13 = var12.q(var0, var1, var3, var7);
                  if (var13 != null) {
                     return var13;
                  }
               }

               var12 = new aai.iZ(aai.Nt.RF, 1, 8, var4);
               var13 = var12.q(var0, var1, var3, var7);
               if (var13 != null) {
                  return var13;
               } else {
                  if (var14) {
                     var12 = new aai.iZ(aai.Nt.RF, 2, 6, var4);
                     var13 = var12.q(var0, var1, var3, var7);
                     if (var13 != null) {
                        return var13;
                     }
                  }

                  return var4.size() == 1 ? false : false;
               }
            } else {
               return false;
            }
         }
      }
   }

   private static boolean q(long var0, int var2, TreeMap var3) {
      return q(var0, var2, 2, var3);
   }

   private static boolean RF(long var0, int var2, TreeMap var3) {
      return q(var0, var2, 4, var3);
   }

   private static boolean xK(long var0, int var2, TreeMap var3) {
      return q(var0, var2, 8, var3);
   }

   private static boolean q(long var0, int var2, int var3, TreeMap var4) {
      if (var2 % var3 != 0) {
         return false;
      } else {
         if (var4 != null && !var4.isEmpty()) {
            for (Long var5 = (Long)var4.ceilingKey(var0); var5 != null && var5 < var0 + var2; var5 = (Long)var4.higherKey(var5)) {
               if ((var5 - var0) % var3 != 0L) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   private static Integer q(IVirtualMemory var0, Integer var1, int var2) {
      byte var3 = (byte)(var1 >>> 8 & 0xFF);
      byte var4 = (byte)(var1 & 0xFF);
      if (var0.getStandardEndianess() == Endianness.LITTLE_ENDIAN) {
         byte var5 = var3;
         var3 = var4;
         var4 = var5;
      }

      if (var2 == 10 && q(var3) && q(var4)) {
         return Integer.parseInt(new String(new byte[]{var3, var4}, Charsets.UTF_8), var2);
      } else {
         return var2 == 16 && RF(var3) && RF(var4) ? Integer.parseInt(new String(new byte[]{var3, var4}, Charsets.UTF_8), var2) : null;
      }
   }

   private static final boolean q(byte var0) {
      return var0 >= 48 && var0 <= 57;
   }

   private static final boolean RF(byte var0) {
      return var0 >= 48 && var0 <= 57 || var0 >= 65 && var0 <= 70 || var0 >= 97 && var0 <= 102;
   }

   INativeContinuousItem RF(long var1, int var3, String var4, int var5, aai.oM var6) {
      if (var6.nf.size() > 2000) {
         return null;
      } else {
         StringBuilder var7 = new StringBuilder("AUTO_GENERATED_");
         ArrayList var8 = new ArrayList();
         ArrayList var9 = new ArrayList();
         ArrayList var10 = new ArrayList();
         long var12 = var1;

         for (Entry var15 : var6.nf.entrySet()) {
            if (var12 != (Long)var15.getKey()) {
               q.catchingSilent(new Exception(Strings.ff("Misaligned structure at %xh", var1)));
               return null;
            }

            var12 += ((Integer)var15.getValue()).intValue();
            INativeContinuousItem var16 = var6.gP.containsKey(var15.getKey()) ? this.gO.getModel().getItemAt((Long)var15.getKey()) : null;
            Object var17 = null;
            boolean var18 = var16 != null && (var16.getName() == null || var16.getName().startsWith("gvar_") || var16.getName().startsWith("ptr_"));
            if (var16 instanceof INativeDataItem) {
               var17 = ((INativeDataItem)var16).getType();
            }

            if (var16 == null && var6.za.containsKey(var15.getKey())) {
               var16 = this.gO.getModel().getItemAt((Long)var15.getKey());
               int var19 = var16 == null ? 1 : ((axw)var16).getStringType().getBasicCharSize();
               IPrimitiveType var20 = this.gP.getPrimitives().getIntegerBySize(var19, true);
               var17 = this.gP.createArray(var20, (Integer)var15.getValue());
            }

            if (var17 == null || ((INativeType)var17).getSize() != (Integer)var15.getValue() && var18) {
               var17 = this.gP.getExactInteger((Integer)var15.getValue(), false);
               if (var17 == null) {
                  return null;
               }
            }

            if (var6.gO || aaj.q(this.gO, (Long)var15.getKey(), (Integer)var15.getValue(), aaj.q(var16), (INativeType)var17, -1L) != null) {
               var17 = this.gO.getTypeManager().getVoidReference();
            } else if ((Integer)var15.getValue() == var5) {
               Long var30 = VirtualMemoryUtil.readAsUnsignedLongSafe(
                  this.gO.getMemory(), this.gO.getProcessor().getEndianness(), (Long)var15.getKey(), (Integer)var15.getValue()
               );
               if (var30 != null && this.nf.RF(var30)) {
                  var17 = this.gO.getTypeManager().getVoidReference();
               }
            }

            var8.add(var17);
            if (var16 != null && !var18 && ((Long)var15.getKey() != var1 || !((axi)var16).RF(true).equals(var4))) {
               String var31 = var16 instanceof axi ? ((axi)var16).RF(true) : var16.getName();
               var9.add(var31);
               var31 = aak.q(var4, var31);
               var10.add(var31);
            } else {
               var9.add(null);
               var10.add(((INativeType)var17).getName());
            }
         }

         String var23 = var7.toString();
         boolean var25 = true;
         String var26 = this.q(var4, var5, var8, var10);
         if (var26 != null) {
            var23 = var26;
            var25 = false;
         }

         StringBuilder var27 = new StringBuilder();

         for (int var28 = 0; var28 < var23.length(); var28++) {
            char var33 = var23.charAt(var28);
            var27.append(Character.isJavaIdentifierPart(var33) ? var33 : '_');
         }

         var23 = var27.toString();
         INativeType var11 = this.q(var23, var26, var25, var8, var10);
         if (var11.getSize() > var3) {
            if (var11 != null && var25) {
               this.gP.deleteType(var11);
            }

            return null;
         } else {
            INativeDataItem var29 = this.gO.defineData(var1, var11);
            int var34 = 0;

            for (Entry var21 : var6.nf.entrySet()) {
               String var22 = (String)var9.get(var34);
               if (var22 != null) {
                  this.gO.getModel().getLabelManager().setLabel((Long)var21.getKey(), var22, true, true, false);
               }

               var34++;
            }

            return var29;
         }
      }
   }

   private INativeType q(String var1, List var2, List var3) {
      return this.q(var1, null, true, var2, var3);
   }

   private INativeType q(String var1, String var2, boolean var3, List var4, List var5) {
      INativeType var6 = this.gP.getType(var1 + (var2 == null ? "1" : ""));
      if (var6 == null || var6 != null && var3) {
         if (var6 == null) {
            if (var3) {
               var1 = var1 + "1";
            }
         } else {
            int var7 = 1;

            do {
               var6 = this.gP.getType(var1 + ++var7);
            } while (var6 != null);

            var1 = var1 + var7;
         }

         IStructureType var10 = this.gP.createStructure(var1);
         var10.setFlags(4096);

         for (int var8 = 0; var8 < var4.size(); var8++) {
            this.gP.addStructureField(var10, (String)var5.get(var8), (INativeType)var4.get(var8));
         }

         var6 = var10;
      }

      return var6;
   }

   private String q(String var1, int var2, List var3, List var4) {
      String var5 = aak.q(var1, var2, var3, var4);
      if (var5 != null) {
         return var5;
      } else {
         HashSet var6 = new HashSet(var4);
         return var6.size() == 1 ? "AUTO_" + (String)var4.get(0) + "_" + var4.size() + "_array" : null;
      }
   }

   private static class CU implements aai.ej {
      List q;

      @Override
      public int q() {
         int var1 = 0;

         for (INativeType var3 : this.q) {
            var1 += var3.getSize();
         }

         return var1;
      }
   }

   private static enum Nt {
      q,
      RF,
      xK;
   }

   public interface ej {
      int q();
   }

   private static class eo implements aai.ej {
      int q;
      int RF;

      public eo(int var1, int var2) {
         this.q = var1;
         this.RF = var2;
      }

      @Override
      public int q() {
         return this.RF * this.q;
      }
   }

   private static class iZ {
      private final aai.Nt RF;
      private final int xK;
      private final int Dw;
      aai.iZ.eo[] q = null;
      private Set Uv;

      public iZ(aai.Nt var1, int var2, int var3, Set var4) {
         this(var1, var2, 1, var3, var4);
      }

      public iZ(aai.Nt var1, int var2, int var3, int var4, Set var5) {
         this.RF = var1;
         this.Dw = var2;
         this.xK = var4;
         this.Uv = var5;
         this.q = new aai.iZ.eo[var3];

         for (int var6 = 0; var6 < var3; var6++) {
            this.q[var6] = new aai.iZ.eo();
         }

         if (var1 == aai.Nt.xK) {
            this.q[0].Dw = 0;
         }
      }

      public boolean q(int var1, long... var2) {
         for (int var3 = 0; var3 < this.q.length; var3++) {
            this.q[var3].q(var1, var2[var3]);
            if (this.q[var3].Dw >= 0 && var1 - this.q[var3].Dw + 1 >= this.xK) {
               this.Uv.clear();
               if (var2.length > 1) {
                  this.Uv.add(new aai.eo(this.Dw, var2.length));
               } else {
                  this.Uv.add(aai.nI.q(this.Dw));
               }

               return true;
            }
         }

         return false;
      }

      public Boolean q(IVirtualMemory var1, long var2, int var4, int var5) {
         long var6 = var2;
         int var8 = var4;
         boolean var9 = var5 >= 0;
         if (var9) {
            var6 = var2 + var5 - (this.xK - 1) * this.Dw;
            var8 = Math.min((this.xK - 1) * 2 * this.Dw + (var1.getSpaceBits() >>> 3), var4 - var5);
         }

         for (int var10 = 0; var10 * this.q.length * this.Dw < var8; var10++) {
            long[] var11 = new long[this.q.length];

            for (int var12 = 0; var12 < this.q.length; var12++) {
               int var13 = var10 * this.q.length * this.Dw + var12 * this.Dw;
               Long var14 = VirtualMemoryUtil.readAsLongSafe(var1, var6 + var13, this.Dw);
               if (var14 == null) {
                  return Boolean.FALSE;
               }

               var11[var12] = var14;
            }

            if (this.q(var10, var11)) {
               return Boolean.TRUE;
            }
         }

         return null;
      }

      private class eo {
         private Long RF = null;
         private Boolean xK = null;
         private int Dw = -1;

         public void q(int var1, long var2) {
            if (iZ.this.RF != aai.Nt.xK || var2 != 0L && var2 != -1L) {
               if (this.RF == null) {
                  this.RF = var2;
                  this.Dw = var1;
               } else if (this.xK != null && !this.xK || (iZ.this.RF != aai.Nt.q || this.RF + 1L != var2) && (iZ.this.RF != aai.Nt.RF || this.RF >= var2)) {
                  if (this.xK != null && this.xK || (iZ.this.RF != aai.Nt.q || this.RF - 1L != var2) && (iZ.this.RF != aai.Nt.RF || this.RF <= var2)) {
                     if (iZ.this.RF != aai.Nt.xK || this.RF != var2) {
                        this.RF = var2;
                        this.xK = null;
                        this.Dw = var1;
                     }
                  } else {
                     this.xK = Boolean.FALSE;
                     this.RF = var2;
                  }
               } else {
                  this.xK = Boolean.TRUE;
                  this.RF = var2;
               }
            } else {
               this.RF = null;
               this.Dw = var1 + 1;
            }
         }

         public void q(int var1) {
            this.RF = null;
            this.xK = null;
            this.Dw = var1;
         }
      }
   }

   public static class nI implements aai.ej {
      private static aai.nI q = new aai.nI(1);
      private static aai.nI RF = new aai.nI(2);
      private static aai.nI xK = new aai.nI(4);
      private static aai.nI Dw = new aai.nI(8);
      private int Uv;

      public nI(int var1) {
         this.Uv = var1;
      }

      @Override
      public int q() {
         return this.Uv;
      }

      @Override
      public int hashCode() {
         return Objects.hash(this.Uv);
      }

      @Override
      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (var1 == null) {
            return false;
         } else if (this.getClass() != var1.getClass()) {
            return false;
         } else {
            aai.nI var2 = (aai.nI)var1;
            return this.Uv == var2.Uv;
         }
      }

      public static aai.nI q(int var0) {
         switch (var0) {
            case 1:
               return q;
            case 2:
               return RF;
            case 3:
            case 5:
            case 6:
            case 7:
            default:
               return new aai.nI(var0);
            case 4:
               return xK;
            case 8:
               return Dw;
         }
      }
   }

   static class oM implements Iterable {
      boolean q;
      boolean RF = false;
      private Set xK = new TreeSet();
      private Set Dw = new TreeSet();
      private Set Uv = new TreeSet();
      private Set oW = new TreeSet();
      private boolean gO = false;
      private Map nf = new TreeMap();
      private TreeMap gP = new TreeMap();
      private Map za = new TreeMap();
      private TreeMap lm = new TreeMap();
      private TreeSet zz = new TreeSet();
      private Set JY = new HashSet();

      public oM(INativeCodeAnalyzer var1, aaj var2, long var3, String var5, int var6) throws JebException {
         int var7 = var1.getTypeManager().getPointerSize();
         byte var8 = 0;
         INativeContinuousItem var9 = null;

         for (int var10 = 0; var10 < var6; var10++) {
            var9 = var1.getModel().getItemAt(var3 + var10);
            if (var9 != null && !(var9 instanceof INativeDataItem)) {
               throw new JebException("Code/Routine detected");
            }

            if (var9 instanceof axr) {
               this.q = true;
            }

            boolean var11 = this.q(var9, var10, var7, var5);
            int var12 = var11 ? -1 : aai.q(var1, var9, var10, var3, var6, var7);
            Set var13 = var1.getModel().getReferenceManager().getReferencesTo(var3 + var10);
            if (var11 || var12 >= 0 || !var13.isEmpty() || var10 - var8 == var7) {
               this.zz.add(var3 + var10);
            }

            if (!var13.isEmpty()) {
               this.lm.put(var3 + var10, var13);
            }

            if (var11) {
               this.gP.put(var3 + var10, (int)var9.getMemorySize());
               if (var10 != 0 && !aaj.RF(var9)) {
                  this.RF = true;
               }

               var10 += (int)var9.getMemorySize() - 1;
            } else if (var12 >= 0) {
               this.za.put(var3 + var10, var12);
               var10 += var12 - 1;
            }
         }

         HashMap var20 = new HashMap();
         TreeMap var21 = new TreeMap();
         int var22 = 0;

         while (var22 < var6) {
            Long var23 = (Long)this.zz.ceiling(var3 + var22);
            Integer var14 = null;
            boolean var15 = var23 != null && var23 == var3 + var22;
            if (var15) {
               var14 = (Integer)this.gP.get(var23);
               if (var14 == null) {
                  var14 = (Integer)this.za.get(var23);
               }

               if (var14 == null) {
                  var15 = false;
                  var23 = (Long)this.zz.ceiling(var3 + var22 + 1L);
               }
            }

            if (var14 == null) {
               var14 = var23 == null ? var6 - var22 : (int)(var23 - (var3 + var22));
               var14 = Math.min(var14, var6 - var22);
               if (var14 == 1) {
                  this.q(var14);
                  this.nf.put(var3 + var22, var14);
                  var22 += var14;
                  continue;
               }

               if (var14 > var7) {
                  var14 = var7;
               }
            }

            Long var16 = VirtualMemoryUtil.readAsLongSafe(var1.getMemory(), var3 + var22, var14);
            if (var16 != null && var16 == 0L && var14 == var7) {
               this.oW.add(var3 + var22);
            } else if (var16 != null && var14 == var7) {
               if (var1.getAnalysisRanges().contains(var16)
                  && !aaj.xK(var1, var16)
                  && !aai.q(var1.getMemory(), var3, var6, new HashSet(), this.lm, false, var22)) {
                  Couple var17 = aaj.q(var1, var3 + var22, var14, aaj.q(var9), var1.getTypeManager().getInteger(var14, false), -1L);
                  if ((var17 == null || (Long)var17.getFirst() != ((INativeContinuousItem)var17.getSecond()).getMemoryAddress()) && !var2.q(var16)) {
                     if (var17 != null) {
                        this.Dw.add(var3 + var22);
                     }
                  } else {
                     var15 = true;
                     this.xK.add(var3 + var22);
                  }
               } else {
                  this.Uv.add(var3 + var22);
               }
            }

            if (var15) {
               this.nf.put(var3 + var22, var14);
               var22 += var14;
               this.q(var14);
            } else if (var16 != null && var16 == 0L) {
               this.nf.put(var3 + var22, var14);
               var22 += var14;
            } else if (var16 == null) {
               if (var14 % 2 != 0) {
                  for (int var28 = 0; var28 < var14; var28++) {
                     this.JY.add(aai.nI.q);
                     var21.put(var3 + var22 + var28, 1);
                  }
               } else {
                  for (byte var29 = 0; var29 < var14; var29 += 2) {
                     this.JY.add(aai.nI.RF);
                     var21.put(var3 + var22 + var29, 2);
                  }
               }

               var22 += var14;
            } else {
               int var27 = aaj.q(var16, var14);
               if (var27 == var14) {
                  this.nf.put(var3 + var22, var14);
                  this.q(var27);
               } else if (var27 > 0) {
                  int var18 = var14 / var27;

                  for (int var19 = 0; var19 < var18; var19++) {
                     var21.put(var3 + var22 + var27 * var19, var27);
                  }

                  this.q(var27);
               } else {
                  var21.put(var3 + var22, var14);
                  if (var3 + var22 + var14.intValue() != var3 + var6) {
                     aai.nI var30 = aai.nI.q(var14);
                     Integer var31 = (Integer)var20.get(var30);
                     var20.put(var30, var31 == null ? 1 : var31 + 1);
                  }
               }

               var22 += var14;
            }
         }

         if (this.JY.isEmpty()) {
            for (Entry var26 : var20.entrySet()) {
               if ((Integer)var26.getValue() > 2) {
                  this.JY.add((aai.ej)var26.getKey());
               }
            }

            if (this.JY.isEmpty()) {
               this.JY.addAll(var20.keySet());
            }
         }

         this.JY.removeIf(var1x -> (var6 & 65535) % var1x.q() != 0);
         this.nf.putAll(var21);
      }

      private void q(int var1) {
         this.JY.add(aai.nI.q(var1));
      }

      private boolean q(INativeContinuousItem var1, int var2, int var3, String var4) {
         if (var1 == null) {
            return false;
         } else {
            return !(var1 instanceof axv) && !(var1 instanceof axw) ? true : !aaj.RF(var1) && (var2 != 0 || !((axi)var1).RF(true).equals(var4));
         }
      }

      public boolean q(long var1) {
         return this.lm.isEmpty() || this.lm.size() == 1 && (Long)this.lm.firstKey() == var1;
      }

      @Override
      public Iterator iterator() {
         return this.nf.entrySet().iterator();
      }
   }
}
