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

public class si {
   private static final ILogger pC = GlobalLog.getLogger(si.class);
   private final a A;
   private final yj kS;
   private final ITypeManager wS;

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   private static int pC(INativeCodeAnalyzer var0, INativeContinuousItem var1, int var2, long var3, int var5, int var6) {
      if (var1 instanceof avb var7) {
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

   public si(a var1, yj var2) {
      this.A = var1;
      this.kS = var2;
      this.wS = var1.ld();
   }

   INativeDataItem pC(long var1, int var3, String var4, int var5, si.bO var6) {
      if (YL.pC(var4)) {
         return null;
      } else if (!var6.A && var6.ld.size() <= 1 && (var6.ld.size() != 1 || (Long)var6.ld.firstKey() == var1)) {
         boolean var7 = this.pC(var6.oT, var1);
         if ((var6.kS.size() + var6.E.size()) * var5 != var3
            && var3 % var5 == 0
            && var6.WR.size() == 1
            && ((si.Ws)var6.WR.iterator().next()).pC() == var5
            && var6.UT.isEmpty()) {
            boolean var8 = true;

            for (int var9 = 0; var9 < var3; var9 += var5) {
               if (!var6.kS.contains(var1 + var9) && !var6.E.contains(var1 + var9)) {
                  Long var10 = VirtualMemoryUtil.readAsLongSafe(this.A.getMemory(), var1 + var9, var5);
                  if (var10 == null || !this.A.getAnalysisRanges().contains(var10) || yj.kS(this.A, var10)) {
                     var8 = false;
                     break;
                  }

                  INativeContinuousItem var11 = this.A.ys().getItemOver(var10);
                  if (var11 != null && (var11 instanceof INativeInstructionItem || var11.getMemoryAddress() != var10 && !(var11 instanceof INativeStringItem))) {
                     var8 = false;
                     break;
                  }
               }
            }

            if (var8) {
               for (int var20 = 0; var20 < var3; var20 += var5) {
                  if (!var6.E.contains(var1 + var20)) {
                     var6.kS.add(var1 + var20);
                  }
               }
            }
         }

         if (!var6.kS.isEmpty()) {
            if (var6.kS.size() > var6.E.size() && (var6.kS.size() + var6.E.size()) * var5 == var3) {
               if (var7) {
                  IReferenceType var19 = this.wS.getVoidReference();
                  if (var19 != null) {
                     IArrayType var22 = this.wS.createArray(var19, var6.kS.size() + var6.E.size());
                     return this.A.pC(var1, (aye)var22, -1, true);
                  }
               } else {
                  var6.sY = true;
               }
            }

            return null;
         } else {
            boolean var18 = var6.kS.isEmpty() && var6.gp.isEmpty();
            boolean var21 = this.pC(var1, var3, var6.WR, var6.oT, var18, -1);
            if (var6.WR.size() == 0 && var7) {
               if (var3 % var5 == 0) {
                  var6.pC(var5);
               } else if (var3 % (var5 >>> 1) == 0) {
                  var6.pC(var5 >>> 1);
               }
            }

            if (var6.WR.size() == 1 && (var21 || var18)) {
               si.Ws var23 = (si.Ws)var6.WR.iterator().next();
               int var25 = var23.pC();
               if (var3 % var23.pC() == 0) {
                  if (var23 instanceof si.K) {
                     INativeType var14 = this.wS.getExactInteger(var25, false);
                     if (var14 != null) {
                        IArrayType var15 = this.wS.createArray(var14, var3 / var25);
                        var15.setFlags(4096);
                        return this.A.defineData(var1, var15);
                     }
                  } else {
                     if (var23 instanceof si.Av var12) {
                        INativeType var28 = this.wS.getExactInteger(var12.pC, false);
                        IArrayType var31 = this.wS.createArray(var28, var12.A);
                        IArrayType var33 = this.wS.createArray(var31, var3 / var25);
                        var33.setFlags(4096);
                        return this.A.defineData(var1, var33);
                     }

                     if (var23 instanceof si.Sv var13) {
                        ArrayList var27 = new ArrayList();

                        for (INativeType var16 : var13.pC) {
                           var27.add(var16.getName());
                        }

                        String var30 = "AUTO_GENERATED_";
                        INativeType var32 = this.pC(var30, var13.pC, var27);
                        IArrayType var17 = this.wS.createArray(var32, var3 / var25);
                        var17.setFlags(4096);
                        return this.A.defineData(var1, var17);
                     }
                  }
               }
            }

            if (var21 || var3 % 2 == 0 && (var3 <= var5 || var3 % 4 == 0)) {
               return null;
            } else {
               INativeType var24 = this.wS.getExactInteger(1, false);
               IArrayType var26 = this.wS.createArray(var24, var3);
               var26.setFlags(4096);
               return this.A.defineData(var1, var26);
            }
         }
      } else {
         return null;
      }
   }

   private boolean pC(TreeMap var1, long var2) {
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

   private boolean pC(long var1, int var3, Set var4, TreeMap var5, boolean var6, int var7) {
      return pC(this.A.getMemory(), var1, var3, var4, var5, var6, var7);
   }

   public static boolean pC(IVirtualMemory var0, long var1, int var3, Set var4, TreeMap var5, boolean var6, int var7) {
      long var8 = var1;
      int var10 = var3;
      boolean var11 = var7 >= 0;
      si.cq var12 = new si.cq(1, 1, 7, var4);
      Boolean var13 = var12.pC(var0, var1, var3, var7);
      if (var13 != null) {
         return var13;
      } else {
         boolean var14 = pC(var1, var3, var5);
         if (var14) {
            var12 = new si.cq(1, 2, 4, var4);
            if (var11) {
               var8 = var1 + var7 - (var12.kS - 1) * var12.wS;
               var10 = Math.min((var12.kS - 1) * 2 * var12.wS + (var0.getSpaceBits() >>> 3), var3 - var7);
            }

            si.cq var15 = new si.cq(1, 2, 4, var4);
            si.cq var16 = new si.cq(1, 2, 4, var4);

            for (int var17 = 0; var17 * var12.wS < var10; var17++) {
               Long var18 = VirtualMemoryUtil.readAsLongSafe(var0, var8 + var17 * var12.wS, var12.wS);
               if (var18 == null) {
                  return false;
               }

               if (var12.pC(var17, var18.intValue())) {
                  return true;
               }

               Integer var19 = pC(var0, var18.intValue(), 10);
               if (var19 == null) {
                  var15.pC[0].pC(var17);
               } else if (var15.pC(var17, var19.intValue())) {
                  return true;
               }

               Integer var20 = pC(var0, var18.intValue(), 16);
               if (var20 == null) {
                  var16.pC[0].pC(var17);
               } else if (var16.pC(var17, var20.intValue())) {
                  return true;
               }
            }
         }

         boolean var38 = A(var1, var3, var5);
         if (var38) {
            var12 = new si.cq(1, 4, 4, var4);
            var13 = var12.pC(var0, var1, var3, var7);
            if (var13 != null) {
               return var13;
            }
         }

         boolean var39 = kS(var1, var3, var5);
         if (var39) {
            var12 = new si.cq(1, 4, 2, 4, var4);
            var13 = var12.pC(var0, var1, var3, var7);
            if (var13 != null) {
               return var13;
            }

            var12 = new si.cq(1, 8, 4, var4);
            var13 = var12.pC(var0, var1, var3, var7);
            if (var13 != null) {
               return var13;
            }
         }

         var12 = new si.cq(12, 1, 3, var4);
         var13 = var12.pC(var0, var1, var3, var7);
         if (var13 != null) {
            return var13;
         } else {
            if (var14) {
               var12 = new si.cq(12, 2, 3, var4);
               var13 = var12.pC(var0, var1, var3, var7);
               if (var13 != null) {
                  return var13;
               }
            }

            if (var4.contains(si.K.pC) && var4.contains(si.K.A)) {
               var4.remove(si.K.A);
            }

            if (var6 && !var11) {
               if (var38) {
                  var12 = new si.cq(12, 4, 3, var4);
                  var13 = var12.pC(var0, var1, var3, var7);
                  if (var13 != null) {
                     return var13;
                  }
               }

               var12 = new si.cq(2, 1, 7, var4);
               var13 = var12.pC(var0, var1, var3, var7);
               if (var13 != null) {
                  return var13;
               } else {
                  if (var14) {
                     var12 = new si.cq(2, 2, 6, var4);
                     var13 = var12.pC(var0, var1, var3, var7);
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

   private static boolean pC(long var0, int var2, TreeMap var3) {
      return pC(var0, var2, 2, var3);
   }

   private static boolean A(long var0, int var2, TreeMap var3) {
      return pC(var0, var2, 4, var3);
   }

   private static boolean kS(long var0, int var2, TreeMap var3) {
      return pC(var0, var2, 8, var3);
   }

   private static boolean pC(long var0, int var2, int var3, TreeMap var4) {
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

   private static Integer pC(IVirtualMemory var0, Integer var1, int var2) {
      byte var3 = (byte)(var1 >>> 8 & 0xFF);
      byte var4 = (byte)(var1 & 0xFF);
      if (var0.getStandardEndianess() == Endianness.LITTLE_ENDIAN) {
         byte var5 = var3;
         var3 = var4;
         var4 = var5;
      }

      if (var2 == 10 && pC(var3) && pC(var4)) {
         return Integer.parseInt(new String(new byte[]{var3, var4}, Charsets.UTF_8), var2);
      } else {
         return var2 == 16 && A(var3) && A(var4) ? Integer.parseInt(new String(new byte[]{var3, var4}, Charsets.UTF_8), var2) : null;
      }
   }

   private static final boolean pC(byte var0) {
      return var0 >= 48 && var0 <= 57;
   }

   private static final boolean A(byte var0) {
      return var0 >= 48 && var0 <= 57 || var0 >= 65 && var0 <= 70 || var0 >= 97 && var0 <= 102;
   }

   INativeContinuousItem A(long var1, int var3, String var4, int var5, si.bO var6) {
      if (var6.ys.size() > 2000) {
         return null;
      } else {
         StringBuilder var7 = new StringBuilder("AUTO_GENERATED_");
         ArrayList var8 = new ArrayList();
         ArrayList var9 = new ArrayList();
         ArrayList var10 = new ArrayList();
         long var12 = var1;

         for (Entry var15 : var6.ys.entrySet()) {
            if (var12 != (Long)var15.getKey()) {
               pC.catchingSilent(new Exception(Strings.ff("Misaligned structure at %xh", var1)));
               return null;
            }

            var12 += ((Integer)var15.getValue()).intValue();
            INativeContinuousItem var16 = var6.ld.containsKey(var15.getKey()) ? this.A.ys().getItemAt((Long)var15.getKey()) : null;
            Object var17 = null;
            boolean var18 = var16 != null && (var16.getName() == null || var16.getName().startsWith("gvar_") || var16.getName().startsWith("ptr_"));
            if (var16 instanceof INativeDataItem) {
               var17 = ((INativeDataItem)var16).getType();
            }

            if (var16 == null && var6.gp.containsKey(var15.getKey())) {
               var16 = this.A.ys().getItemAt((Long)var15.getKey());
               int var19 = var16 == null ? 1 : ((avb)var16).getStringType().getBasicCharSize();
               IPrimitiveType var20 = this.wS.getPrimitives().getIntegerBySize(var19, true);
               var17 = this.wS.createArray(var20, (Integer)var15.getValue());
            }

            if (var17 == null || ((INativeType)var17).getSize() != (Integer)var15.getValue() && var18) {
               var17 = this.wS.getExactInteger((Integer)var15.getValue(), false);
               if (var17 == null) {
                  return null;
               }
            }

            if (var6.sY || yj.pC(this.A, (Long)var15.getKey(), (Integer)var15.getValue(), yj.pC(var16), (INativeType)var17, -1L) != null) {
               var17 = this.A.ld().E();
            } else if ((Integer)var15.getValue() == var5) {
               Long var30 = VirtualMemoryUtil.readAsUnsignedLongSafe(
                  this.A.getMemory(), this.A.getProcessor().getEndianness(), (Long)var15.getKey(), (Integer)var15.getValue()
               );
               if (var30 != null && this.kS.A(var30)) {
                  var17 = this.A.ld().E();
               }
            }

            var8.add(var17);
            if (var16 != null && !var18 && ((Long)var15.getKey() != var1 || !((aun)var16).A(true).equals(var4))) {
               String var31 = var16 instanceof aun ? ((aun)var16).A(true) : var16.getName();
               var9.add(var31);
               var31 = YL.pC(var4, var31);
               var10.add(var31);
            } else {
               var9.add(null);
               var10.add(((INativeType)var17).getName());
            }
         }

         String var23 = var7.toString();
         boolean var25 = true;
         String var26 = this.pC(var4, var5, var8, var10);
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
         INativeType var11 = this.pC(var23, var26, var25, var8, var10);
         if (var11.getSize() > var3) {
            if (var11 != null && var25) {
               this.wS.deleteType(var11);
            }

            return null;
         } else {
            INativeDataItem var29 = this.A.pC(var1, (aye)var11, -1, true);
            int var34 = 0;

            for (Entry var21 : var6.ys.entrySet()) {
               String var22 = (String)var9.get(var34);
               if (var22 != null) {
                  this.A.ys().oT().setLabel((Long)var21.getKey(), var22, true, true, false);
               }

               var34++;
            }

            return var29;
         }
      }
   }

   private INativeType pC(String var1, List var2, List var3) {
      return this.pC(var1, null, true, var2, var3);
   }

   private INativeType pC(String var1, String var2, boolean var3, List var4, List var5) {
      INativeType var6 = this.wS.getType(var1 + (var2 == null ? "1" : ""));
      if (var6 == null || var6 != null && var3) {
         if (var6 == null) {
            if (var3) {
               var1 = var1 + "1";
            }
         } else {
            int var7 = 1;

            do {
               var6 = this.wS.getType(var1 + ++var7);
            } while (var6 != null);

            var1 = var1 + var7;
         }

         IStructureType var10 = this.wS.createStructure(var1);
         var10.setFlags(4096);

         for (int var8 = 0; var8 < var4.size(); var8++) {
            this.wS.addStructureField(var10, (String)var5.get(var8), (INativeType)var4.get(var8));
         }

         var6 = var10;
      }

      return var6;
   }

   private String pC(String var1, int var2, List var3, List var4) {
      String var5 = YL.pC(var1, var2, var3, var4);
      if (var5 != null) {
         return var5;
      } else {
         HashSet var6 = new HashSet(var4);
         return var6.size() == 1 ? "AUTO_" + (String)var4.get(0) + "_" + var4.size() + "_array" : null;
      }
   }

   public static int pC(IVirtualMemory var0, Long var1, int var2, int var3, long var4) {
      int var6 = var3 - 2;
      if (var6 < 0) {
         var6 = 0;
      }

      if (var4 >= 65536L) {
         var4 = 65536L;
      }

      if (var4 < var3) {
         return var3;
      } else {
         for (si.cq var7 = new si.cq(6, var2, -1, null); var6 < var4; var6++) {
            Long var8 = VirtualMemoryUtil.readAsUnsignedLongSafe(var0, var1 + var6 * var7.wS, var7.wS);
            if (var8 == null) {
               return var6;
            }

            if (var8 == 0L) {
               var6--;
               break;
            }

            if (!var7.pC(var6, var8.intValue())) {
               break;
            }
         }

         return var3 + 3 >= var6 ? var3 : var6;
      }
   }

   private static class Av implements si.Ws {
      int pC;
      int A;

      public Av(int var1, int var2) {
         this.pC = var1;
         this.A = var2;
      }

      @Override
      public int pC() {
         return this.A * this.pC;
      }
   }

   public static class K implements si.Ws {
      private static si.K pC = new si.K(1);
      private static si.K A = new si.K(2);
      private static si.K kS = new si.K(4);
      private static si.K wS = new si.K(8);
      private int UT;

      public K(int var1) {
         this.UT = var1;
      }

      @Override
      public int pC() {
         return this.UT;
      }

      @Override
      public int hashCode() {
         return Objects.hash(this.UT);
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
            si.K var2 = (si.K)var1;
            return this.UT == var2.UT;
         }
      }

      public static si.K pC(int var0) {
         switch (var0) {
            case 1:
               return pC;
            case 2:
               return A;
            case 3:
            case 5:
            case 6:
            case 7:
            default:
               return new si.K(var0);
            case 4:
               return kS;
            case 8:
               return wS;
         }
      }
   }

   private static class Sv implements si.Ws {
      List pC;

      @Override
      public int pC() {
         int var1 = 0;

         for (INativeType var3 : this.pC) {
            var1 += var3.getSize();
         }

         return var1;
      }
   }

   public interface Ws {
      int pC();
   }

   static class bO implements Iterable {
      boolean pC;
      boolean A = false;
      private Set kS = new TreeSet();
      private Set wS = new TreeSet();
      private Set UT = new TreeSet();
      private Set E = new TreeSet();
      private boolean sY = false;
      private Map ys = new TreeMap();
      private TreeMap ld = new TreeMap();
      private Map gp = new TreeMap();
      private TreeMap oT = new TreeMap();
      private TreeSet fI = new TreeSet();
      private Set WR = new HashSet();

      public bO(INativeCodeAnalyzer var1, yj var2, long var3, String var5, int var6) throws JebException {
         int var7 = var1.getTypeManager().getPointerSize();
         byte var8 = 0;
         INativeContinuousItem var9 = null;

         for (int var10 = 0; var10 < var6; var10++) {
            var9 = var1.getModel().getItemAt(var3 + var10);
            if (var9 != null && !(var9 instanceof INativeDataItem)) {
               throw new JebException("Code/Routine detected");
            }

            if (var9 instanceof auw) {
               this.pC = true;
            }

            boolean var11 = this.pC(var9, var10, var7, var5);
            int var12 = var11 ? -1 : si.pC(var1, var9, var10, var3, var6, var7);
            Set var13 = var1.getModel().getReferenceManager().getReferencesTo(var3 + var10);
            if (var11 || var12 >= 0 || !var13.isEmpty() || var10 - var8 == var7) {
               this.fI.add(var3 + var10);
            }

            if (!var13.isEmpty()) {
               this.oT.put(var3 + var10, var13);
            }

            if (var11) {
               this.ld.put(var3 + var10, (int)var9.getMemorySize());
               if (var10 != 0 && !yj.A(var9)) {
                  this.A = true;
               }

               var10 += (int)var9.getMemorySize() - 1;
            } else if (var12 >= 0) {
               this.gp.put(var3 + var10, var12);
               var10 += var12 - 1;
            }
         }

         HashMap var20 = new HashMap();
         TreeMap var21 = new TreeMap();
         int var22 = 0;

         while (var22 < var6) {
            Long var23 = (Long)this.fI.ceiling(var3 + var22);
            Integer var14 = null;
            boolean var15 = var23 != null && var23 == var3 + var22;
            if (var15) {
               var14 = (Integer)this.ld.get(var23);
               if (var14 == null) {
                  var14 = (Integer)this.gp.get(var23);
               }

               if (var14 == null) {
                  var15 = false;
                  var23 = (Long)this.fI.ceiling(var3 + var22 + 1L);
               }
            }

            if (var14 == null) {
               var14 = var23 == null ? var6 - var22 : (int)(var23 - (var3 + var22));
               var14 = Math.min(var14, var6 - var22);
               if (var14 == 1) {
                  this.pC(var14);
                  this.ys.put(var3 + var22, var14);
                  var22 += var14;
                  continue;
               }

               if (var14 > var7) {
                  var14 = var7;
               }
            }

            Long var16 = VirtualMemoryUtil.readAsLongSafe(var1.getMemory(), var3 + var22, var14);
            if (var16 != null && var16 == 0L && var14 == var7) {
               this.E.add(var3 + var22);
            } else if (var16 != null && var14 == var7) {
               if (var1.getAnalysisRanges().contains(var16)
                  && !yj.kS(var1, var16)
                  && !si.pC(var1.getMemory(), var3, var6, new HashSet(), this.oT, false, var22)) {
                  Couple var17 = yj.pC(var1, var3 + var22, var14, yj.pC(var9), var1.getTypeManager().getInteger(var14, false), -1L);
                  if ((var17 == null || (Long)var17.getFirst() != ((INativeContinuousItem)var17.getSecond()).getMemoryAddress()) && !var2.pC(var16)) {
                     if (var17 != null) {
                        this.wS.add(var3 + var22);
                     }
                  } else {
                     var15 = true;
                     this.kS.add(var3 + var22);
                  }
               } else {
                  this.UT.add(var3 + var22);
               }
            }

            if (var15) {
               this.ys.put(var3 + var22, var14);
               var22 += var14;
               this.pC(var14);
            } else if (var16 != null && var16 == 0L) {
               this.ys.put(var3 + var22, var14);
               var22 += var14;
            } else if (var16 == null) {
               if (var14 % 2 != 0) {
                  for (int var28 = 0; var28 < var14; var28++) {
                     this.WR.add(si.K.pC);
                     var21.put(var3 + var22 + var28, 1);
                  }
               } else {
                  for (byte var29 = 0; var29 < var14; var29 += 2) {
                     this.WR.add(si.K.A);
                     var21.put(var3 + var22 + var29, 2);
                  }
               }

               var22 += var14;
            } else {
               int var27 = yj.pC(var16, var14);
               if (var27 == var14) {
                  this.ys.put(var3 + var22, var14);
                  this.pC(var27);
               } else if (var27 > 0) {
                  int var18 = var14 / var27;

                  for (int var19 = 0; var19 < var18; var19++) {
                     var21.put(var3 + var22 + var27 * var19, var27);
                  }

                  this.pC(var27);
               } else {
                  var21.put(var3 + var22, var14);
                  if (var3 + var22 + var14.intValue() != var3 + var6) {
                     si.K var30 = si.K.pC(var14);
                     Integer var31 = (Integer)var20.get(var30);
                     var20.put(var30, var31 == null ? 1 : var31 + 1);
                  }
               }

               var22 += var14;
            }
         }

         if (this.WR.isEmpty()) {
            for (Entry var26 : var20.entrySet()) {
               if ((Integer)var26.getValue() > 2) {
                  this.WR.add((si.Ws)var26.getKey());
               }
            }

            if (this.WR.isEmpty()) {
               this.WR.addAll(var20.keySet());
            }
         }

         this.WR.removeIf(var1x -> (var6 & 65535) % var1x.pC() != 0);
         this.ys.putAll(var21);
      }

      private void pC(int var1) {
         this.WR.add(si.K.pC(var1));
      }

      private boolean pC(INativeContinuousItem var1, int var2, int var3, String var4) {
         if (var1 == null) {
            return false;
         } else {
            return !(var1 instanceof ava) && !(var1 instanceof avb) ? true : !yj.A(var1) && (var2 != 0 || !((aun)var1).A(true).equals(var4));
         }
      }

      public boolean pC(long var1) {
         return this.oT.isEmpty() || this.oT.size() == 1 && (Long)this.oT.firstKey() == var1;
      }

      @Override
      public Iterator iterator() {
         return this.ys.entrySet().iterator();
      }
   }

   private static class cq {
      private final int A;
      private final int kS;
      private final int wS;
      si.cq.Av[] pC = null;
      private Set UT;

      public cq(int var1, int var2, int var3, Set var4) {
         this(var1, var2, 1, var3, var4);
      }

      public cq(int var1, int var2, int var3, int var4, Set var5) {
         this.A = var1;
         this.wS = var2;
         this.kS = var4;
         this.UT = var5;
         this.pC = new si.cq.Av[var3];

         for (int var6 = 0; var6 < var3; var6++) {
            this.pC[var6] = new si.cq.Av();
         }

         if (var1 == 12) {
            this.pC[0].wS = 0;
         }
      }

      public boolean pC(int var1, long... var2) {
         for (int var3 = 0; var3 < this.pC.length; var3++) {
            this.pC[var3].pC(var1, var2[var3]);
            if (this.pC[var3].wS >= 0 && var1 - this.pC[var3].wS + 1 >= this.kS) {
               this.UT.clear();
               if (var2.length > 1) {
                  this.UT.add(new si.Av(this.wS, var2.length));
               } else {
                  this.UT.add(si.K.pC(this.wS));
               }

               return true;
            }
         }

         return false;
      }

      public boolean pC(int var1, long var2) {
         if (this.pC[0].wS == -2) {
            return false;
         } else {
            int var4 = this.pC[0].wS;
            this.pC[0].pC(var1, var2);
            if (var4 != -1 && this.pC[0].wS != var4) {
               this.pC[0].wS = -2;
               return false;
            } else {
               return true;
            }
         }
      }

      public Boolean pC(IVirtualMemory var1, long var2, int var4, int var5) {
         long var6 = var2;
         int var8 = var4;
         boolean var9 = var5 >= 0;
         if (var9) {
            var6 = var2 + var5 - (this.kS - 1) * this.wS;
            var8 = Math.min((this.kS - 1) * 2 * this.wS + (var1.getSpaceBits() >>> 3), var4 - var5);
         }

         for (int var10 = 0; var10 * this.pC.length * this.wS < var8; var10++) {
            long[] var11 = new long[this.pC.length];

            for (int var12 = 0; var12 < this.pC.length; var12++) {
               int var13 = var10 * this.pC.length * this.wS + var12 * this.wS;
               Long var14 = VirtualMemoryUtil.readAsLongSafe(var1, var6 + var13, this.wS);
               if (var14 == null) {
                  return Boolean.FALSE;
               }

               var11[var12] = var14;
            }

            if (this.pC(var10, var11)) {
               return Boolean.TRUE;
            }
         }

         return null;
      }

      private class Av {
         private Long A = null;
         private Boolean kS = null;
         private int wS = -1;

         public void pC(int var1, long var2) {
            long var4 = (1L << cq.this.wS * 8) - 1L;
            long var6 = cq.this.wS < 8 ? var2 & var4 : var2;
            if ((cq.this.A & 8) == 0 || var6 != 0L && var2 != -1L) {
               if (this.A == null) {
                  this.A = var6;
                  this.wS = var1;
               } else if (this.kS != null && !this.kS || ((cq.this.A & 1) == 0 || this.A + 1L != var6) && ((cq.this.A & 2) == 0 || !this.pC(this.A, var6))) {
                  if (this.kS != null && this.kS || ((cq.this.A & 1) == 0 || this.A - 1L != var6) && ((cq.this.A & 2) == 0 || !this.pC(var6, this.A))) {
                     if ((cq.this.A & 12) == 0 || this.A != var6) {
                        this.A = var6;
                        this.kS = null;
                        this.wS = var1;
                     }
                  } else {
                     this.kS = Boolean.FALSE;
                     this.A = var6;
                  }
               } else {
                  this.kS = Boolean.TRUE;
                  this.A = var6;
               }
            } else {
               this.A = null;
               this.wS = var1 + 1;
            }
         }

         boolean pC(long var1, long var3) {
            if (var1 >= var3) {
               return false;
            } else {
               long var5 = var1 >> 3;
               return var3 < var1 + var5 ? true : var3 <= var1 << 4;
            }
         }

         public void pC(int var1) {
            this.A = null;
            this.kS = null;
            this.wS = var1;
         }
      }
   }
}
