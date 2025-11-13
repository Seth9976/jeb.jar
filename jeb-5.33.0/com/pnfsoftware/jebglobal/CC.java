package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeModel;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReference;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReferenceManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.DataStringUtil;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeStringItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.StringEntry;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrimitiveType;
import com.pnfsoftware.jeb.core.units.code.asm.type.StringEncoding;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CC {
   private static final ILogger pC = GlobalLog.getLogger(CC.class);
   private final INativeCodeAnalyzer A;
   private final INativeCodeModel kS;
   private final IVirtualMemory wS;
   private StringEncoding UT = StringEncoding.ASCII_ZERO;

   private static boolean pC(CC.Sv var0) {
      if (var0.A.isEmpty() && var0.kS.isEmpty()) {
         for (CC.Av var2 : var0.pC) {
            if (!var2.UT && var2.kS <= 3L) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private static List pC(CC.Sv var0, CC.Sv var1) {
      ArrayList var2 = new ArrayList();
      if (var0 != null) {
         var2.addAll(var0.pC);
      }

      if (var1 != null) {
         var2.addAll(var1.pC);
      }

      return var2;
   }

   private static List A(CC.Sv var0, CC.Sv var1) {
      ArrayList var2 = new ArrayList();
      if (var0 != null) {
         var2.addAll(var0.kS);
      }

      if (var1 != null) {
         var2.addAll(var1.kS);
      }

      return var2;
   }

   private static Couple pC(List var0, long var1, long var3) {
      long var5 = var1;
      long var7 = var3;

      for (CC.Av var10 : var0) {
         if (var10.pC < var5) {
            var5 = var10.pC;
         }

         if (var10.A > var7) {
            var7 = var10.A;
         }
      }

      return new Couple(var5, var7);
   }

   public CC(INativeCodeAnalyzer var1) {
      this.A = var1;
      this.kS = var1.getModel();
      this.wS = var1.getMemory();
   }

   public avb pC(long var1, long var3) {
      if (this.A.getAnalysisRanges().contains(var1) && this.kS.getItemOver(var1) == null) {
         if (var3 == -1L) {
            var3 = this.A.getAnalysisRanges().getLocalEnd(var1);
         } else if (Longs.compareUnsigned(var3, var1) <= 0) {
            return null;
         }

         INativeContinuousItem var5 = this.kS.getNextItem(var1);
         if (var5 != null) {
            var3 = Math.min(var3, var5.getMemoryAddress());
         }

         return this.A(var1, var3);
      } else {
         return null;
      }
   }

   public avb A(long var1, long var3) {
      ((Tw)this.kS).pC.verifyLocked();
      INativeContinuousItem var5 = this.kS.getItemAt(var3);
      if (var5 instanceof INativeDataItem var6 && yj.kS(var6) && TypeUtil.getNonAlias(var6.getType()) instanceof IPrimitiveType) {
         Long var7 = VirtualMemoryUtil.readAsUnsignedLongSafe(this.wS, var3, (int)var5.getMemorySize());
         if (var7 != null && var7 == 0L) {
            var3++;
         }
      }

      avb var11 = ((a)this.A).pC(var1, var3, null, 4, 4096, true);
      List var12 = null;
      if (var11 != null || DataStringUtil.isSafeAsciiStringAt(this.wS, var1, 1, 4)) {
         var12 = this.pC(var1, var3, 4, 4096, var11, false);
      }

      if (var12 != null) {
         for (CC.Ws var9 : var12) {
            if (var9.pC + var9.kS > var3) {
               Object[] var10000 = new Object[]{var9.pC};
            } else {
               avb var10 = ((a)this.A).pC(var9.pC, var3, var9.A, var9.kS, var9.wS, true);
               if (var11 == null && var9.pC == var1) {
                  var11 = var10;
               }
            }
         }
      }

      return var11;
   }

   private List pC(long var1, long var3, int var5, int var6, avb var7, boolean var8) {
      if (var5 == 0) {
         return null;
      } else {
         CC.Sv var9 = this.pC(var1, var7, 10);
         this.pC(var1, var7, var9.pC);
         var9.pC(this.wS, this.UT);
         ArrayList var10 = new ArrayList();
         if (var7 == null) {
            boolean var11 = DataStringUtil.isSafeAsciiStringAt(this.wS, var1, this.UT, 2, var5);
            if (!var11) {
               boolean var12 = DataStringUtil.isSafeAsciiStringAt(this.wS, var1, 2, var5);
               if (!var12) {
                  return null;
               }
            }

            var10.add(new CC.Ws(var1, var11 ? this.UT : StringEncoding.ASCII_ZERO, 2, var5));
         }

         long var25 = var7 != null ? var7.getMemoryAddressEnd() : var1;
         CC.Sv var13 = this.kS(var25, 10);
         var13.pC(this.wS, this.UT);
         long var14;
         if (var7 == null) {
            CC.Av var16 = var13.pC(var1);
            if (var16 == null) {
               return null;
            }

            var14 = var16.A;
         } else {
            var14 = var7.getMemoryAddressEnd();
         }

         if (var7 == null && !var8 && var9.pC.size() + var13.pC.size() < 10) {
            return null;
         } else if (pC(var9) && pC(var13)) {
            return var10;
         } else {
            List var26 = pC(var9, var13);
            if (var26.isEmpty()) {
               return var10;
            } else {
               List var17 = A(var9, var13);
               CC.bO var18 = new CC.bO(var7 != null, var1, var14);
               var18.pC(var9, true);
               var18.pC(var13, false);
               boolean var19 = var26.size() + var17.size() >= 10;

               for (CC.Av var21 : var26) {
                  if (!var21.UT && (var21.kS == 3L || var21.kS < 3L && (var19 || var18.pC(var21.pC)))) {
                     var10.add(new CC.Ws(var21.pC, var21.wS, (int)var21.kS, (int)var21.kS * 2 + 2));
                  }
               }

               if (var19) {
                  ArrayList var27 = new ArrayList();
                  var27.addAll(var17);
                  boolean var28 = var18.pC();
                  if (this.UT.getBasicCharSize() == 1 || var28) {
                     var27.addAll(var9.A);
                     var27.addAll(var13.A);
                  }

                  Couple var22 = pC(var26, var1, var14);

                  for (Long var24 : var27) {
                     if (var28 || var24 > (Long)var22.getFirst() && var24 < (Long)var22.getSecond()) {
                        var10.add(new CC.Ws(var24, StringEncoding.ASCII_ZERO, 1, 2));
                     }
                  }
               }

               return var10;
            }
         }
      }
   }

   private StringEncoding pC(long var1, avb var3, List var4) {
      if (this.UT == StringEncoding.UTF16_LE_ZERO) {
         return this.UT;
      } else {
         this.UT = this.A(var1, var3, var4);
         return this.UT;
      }
   }

   private StringEncoding A(long var1, avb var3, List var4) {
      if (var3 != null && var3.getStringType().getBasicCharSize() == 2) {
         return var3.getStringType();
      } else {
         int var5 = 10;
         long var6 = var1;
         if (!var4.isEmpty()) {
            for (CC.Av var9 : var4) {
               if (var9.UT) {
                  if (var9.wS.getBasicCharSize() == 2) {
                     return var9.wS;
                  }

                  var5--;
               }

               var6 = var9.pC;
            }
         }

         while (var5 > 0) {
            INativeContinuousItem var13 = this.kS.getPreviousItem(var6);
            if (!(var13 instanceof INativeStringItem)) {
               break;
            }

            StringEncoding var15 = ((INativeStringItem)var13).getStringType();
            if (var15.getBasicCharSize() == 2) {
               return var15;
            }

            var6 = var13.getMemoryAddress() - 1L;
            var5--;
         }

         var5 = 5;

         for (long var14 = var1 + 1L; var5 > 0; var5--) {
            INativeContinuousItem var10 = this.kS.getNextItem(var14);
            if (!(var10 instanceof INativeStringItem)) {
               break;
            }

            StringEncoding var11 = ((INativeStringItem)var10).getStringType();
            if (var11.getBasicCharSize() == 2) {
               return var11;
            }

            var14 = var10.getMemoryAddress() + 1L;
         }

         return this.UT;
      }
   }

   INativeContinuousItem pC(long var1) {
      return this.pC(var1, 5);
   }

   INativeContinuousItem pC(long var1, int var3) {
      INativeContinuousItem var4 = this.kS.getPreviousItem(var1);

      while (var4 != null && this.pC(var4) && var3 > 0) {
         var4 = this.kS.getPreviousItem(var4.getMemoryAddress());
         var3--;
      }

      return var4;
   }

   INativeContinuousItem A(long var1) {
      return this.A(var1, 5);
   }

   INativeContinuousItem A(long var1, int var3) {
      INativeContinuousItem var4 = this.kS.getNextItem(var1);

      while (var4 != null && this.pC(var4) && var3 > 0) {
         var4 = this.kS.getNextItem(var4.getMemoryAddress());
         var3--;
      }

      return var4;
   }

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   private CC.Sv pC(long var1, avb var3, int var4) {
      CC.Sv var5 = new CC.Sv();
      List var6 = var5.pC;
      CC.K var7 = new CC.K();
      long var8 = var1;
      int var10 = this.pC((INativeStringItem)var3);

      while (var6.size() < var4) {
         int var11 = var6.size();
         INativeContinuousItem var12 = this.pC(var8);
         var10 = Math.max(var10, this.kS(var12));
         int var13 = 3;
         boolean var14 = false;
         long var15 = 0L;

         boolean var17;
         for (var17 = false; var13 > 0; var12 = this.pC(var12.getMemoryAddress())) {
            var14 = false;
            var15 = var12 != null ? var12.getMemoryAddressEnd() : 0L;
            var17 = false;
            if (var12 instanceof INativeStringItem) {
               if (var12.getMemoryAddressEnd() == var8) {
                  var6.add(new CC.Av((INativeStringItem)var12));
                  break;
               }

               if (var8 - var12.getMemoryAddressEnd() >= var10) {
                  var14 = true;
                  break;
               }

               try {
                  for (long var34 = var12.getMemoryAddressEnd(); var34 < var8; var34++) {
                     if (this.wS.readByte(var34) != 0) {
                        var14 = true;
                        break;
                     }
                  }

                  if (!var14) {
                     var6.add(new CC.Av((INativeStringItem)var12));
                  }
                  break;
               } catch (MemoryException var30) {
                  return var5;
               }
            }

            boolean var18 = false;
            if (var12 instanceof INativeDataItem) {
               if (var12.getMemorySize() == 1L && "unsigned char".equals(((INativeDataItem)var12).getType().getSignature())) {
                  var18 = true;
               } else if (var12 instanceof ava && var12.getName(true).startsWith("gvar_") && !var7.pC(var12.getMemoryAddress(), null, var1)) {
                  var18 = true;
               }

               if (var18) {
                  var15 = var12.getMemoryAddress();
                  var17 = true;
               }
            }

            var14 = true;
            if (!var18) {
               break;
            }

            var13--;
         }

         long var35 = var8 - 1L;
         if (var14) {
            int var21;
            try {
               var21 = 0;
            } catch (MemoryException var28) {
               return var5;
            }

            byte var20;
            do {
               try {
                  var20 = this.wS.readByte(var35);
                  if (var20 != 0) {
                     break;
                  }

                  if (var7.pC(var35, null, var1)) {
                     return var5;
                  }
               } catch (MemoryException var33) {
                  return var5;
               }

               var35--;
            } while (++var21 < var10);

            if (var21 == 0) {
               return var5;
            }

            if (var21 == var10) {
               return var5;
            }

            long var22;
            boolean var24;
            int var25;
            try {
               var22 = var35 + 1L;
               var24 = false;
               if (var17 && var12 != null && var35 + 1L < var15) {
                  var24 = true;
               }

               var25 = 0;
            } catch (MemoryException var27) {
               return var5;
            }

            while (true) {
               label209: {
                  try {
                     if (this.pC(var20) && (var12 == null || var35 >= var15) && var25 < 4096) {
                        if (!var7.pC(var35, null, var1)) {
                           break label209;
                        }

                        return var5;
                     }
                  } catch (MemoryException var32) {
                     return var5;
                  }

                  try {
                     if (var35 < var15 && var12 instanceof INativeInstructionItem) {
                        return var5;
                     }
                  } catch (MemoryException var29) {
                     return var5;
                  }

                  try {
                     if (var20 == 0 || var12 != null && var35 + 1L == var15) {
                        if (var25 >= 2) {
                           var6.add(new CC.Av(var35 + 1L, var22 + 1L, var25, false));
                        } else {
                           if (!var5.A.contains(var35 + 3L) && var5.A.size() >= 30) {
                              return var5;
                           }

                           var5.A.add(var35 + 1L);
                        }
                        break;
                     }
                  } catch (MemoryException var31) {
                     return var5;
                  }

                  if (!var24) {
                     return var5;
                  }

                  var35++;
                  break;
               }

               try {
                  var35--;
                  var25++;
                  var20 = this.wS.readByte(var35);
               } catch (MemoryException var26) {
                  return var5;
               }
            }
         }

         if (var6.size() == var11) {
            var8 = var35 + 1L;
         } else {
            var8 = ((CC.Av)var6.get(var6.size() - 1)).pC;
         }
      }

      return var5;
   }

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   private CC.Sv kS(long var1, int var3) {
      CC.Sv var4 = new CC.Sv();
      List var5 = var4.pC;
      CC.K var6 = new CC.K();
      long var7 = var1;
      int var9 = this.pC(this.UT);

      while (var5.size() < var3) {
         int var10 = var5.size();
         INativeContinuousItem var11 = this.kS.getNextItem(var7 - 1L);
         boolean var12 = false;
         long var13 = var11 != null ? var11.getMemoryAddress() : Long.MAX_VALUE;
         if (var11 != null && var11.hasAttribute("UnknownSize") && yj.A(var11)) {
            var11 = this.kS.getNextItem(var11);
            var13 = var11 != null ? var11.getMemoryAddress() : Long.MAX_VALUE;
         }

         if (var11 instanceof INativeStringItem) {
            if (var11.getMemoryAddress() == var7) {
               var5.add(new CC.Av((INativeStringItem)var11));
            } else if (var11.getMemoryAddress() - var7 >= var9) {
               var12 = true;
            } else {
               try {
                  for (long var30 = var7; var30 < var11.getMemoryAddress(); var30++) {
                     if (this.wS.readByte(var30) != 0) {
                        var12 = true;
                        break;
                     }
                  }

                  if (!var12) {
                     var5.add(new CC.Av((INativeStringItem)var11));
                  }
               } catch (MemoryException var26) {
                  return var4;
               }
            }
         } else {
            if (var11 instanceof INativeDataItem && var11.getMemorySize() == 1L && "unsigned char".equals(((INativeDataItem)var11).getType().getSignature())) {
               INativeContinuousItem var15 = this.kS.getNextItem(var11.getMemoryAddress());
               var13 = var11.getMemoryAddress() + 4L;
               if (var15 != null && var15.getMemoryAddress() < var13) {
                  var13 = var15.getMemoryAddress();
               }
            }

            var12 = true;
         }

         long var31 = var7;
         if (var12) {
            int var18;
            try {
               var18 = 0;
            } catch (MemoryException var24) {
               return var4;
            }

            byte var17;
            do {
               try {
                  var17 = this.wS.readByte(var31);
                  if (var17 != 0) {
                     break;
                  }

                  if (var6.pC(var31, var1, null)) {
                     return var4;
                  }
               } catch (MemoryException var29) {
                  return var4;
               }

               var31++;
            } while (++var18 < var9);

            if (var18 == var9) {
               return var4;
            }

            long var19;
            int var21;
            try {
               var19 = var31;
               var21 = 0;
            } catch (MemoryException var23) {
               return var4;
            }

            while (true) {
               label181: {
                  try {
                     if (this.pC(var17) && (var11 == null || var31 < var13) && var21 < 4096) {
                        if (!var6.pC(var31, var1, null)) {
                           break label181;
                        }

                        return var4;
                     }
                  } catch (MemoryException var28) {
                     return var4;
                  }

                  try {
                     if (var31 >= var13 && var11 instanceof INativeInstructionItem) {
                        return var4;
                     }
                  } catch (MemoryException var27) {
                     return var4;
                  }

                  try {
                     if (var17 != 0) {
                        return var4;
                     }

                     if (var21 >= 2) {
                        var5.add(new CC.Av(var19, var31 + 1L, var21, false));
                     } else {
                        if (!var4.A.contains(var19 - 2L) && var4.A.size() >= 30) {
                           return var4;
                        }

                        var4.A.add(var19);
                     }
                     break;
                  } catch (MemoryException var25) {
                     return var4;
                  }
               }

               try {
                  var31++;
                  var21++;
                  var17 = this.wS.readByte(var31);
               } catch (MemoryException var22) {
                  return var4;
               }
            }
         }

         if (var5.size() == var10) {
            var7 = var31;
         } else {
            var7 = ((CC.Av)var5.get(var5.size() - 1)).A;
         }
      }

      return var4;
   }

   private long kS(long var1) {
      return this.wS.getSpaceBits() == 64 ? var1 & -8L : var1 & 4294967292L;
   }

   private long pC() {
      return this.wS.getSpaceBits() == 64 ? 8L : 4L;
   }

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   public boolean pC(INativeContinuousItem var1) {
      byte[] var2 = new byte[(int)var1.getMemorySize()];

      byte[] var3;
      int var4;
      int var5;
      try {
         this.wS.read(var1.getMemoryAddress(), var2.length, var2, 0);
         var3 = var2;
         var4 = var2.length;
         var5 = 0;
      } catch (MemoryException var7) {
         return false;
      }

      while (true) {
         try {
            if (var5 >= var4) {
               return true;
            }

            byte var6 = var3[var5];
            if (var6 != 0) {
               return false;
            }
         } catch (MemoryException var8) {
            return false;
         }

         var5++;
      }
   }

   private int kS(INativeContinuousItem var1) {
      return var1 instanceof INativeStringItem ? this.pC((INativeStringItem)var1) : this.A();
   }

   private int pC(INativeStringItem var1) {
      return var1 != null ? this.pC(var1.getStringType()) : this.A();
   }

   private int pC(StringEncoding var1) {
      return 32 + (var1.getBasicCharSize() == 1 ? 1 : 3);
   }

   private int A() {
      return 33;
   }

   private boolean pC(byte var1) {
      return var1 >= 32 && var1 <= 126 || var1 == 13 || var1 == 10 || var1 == 9;
   }

   public avb pC(long var1, int var3, int var4, boolean var5, boolean var6, boolean var7) {
      avb var8 = this.pC(var1, var3, var4, var5);
      if (var8 == null && DataStringUtil.isSafeAsciiStringAt(this.wS, var1, 1, 4096)) {
         List var9 = this.pC(var1, var1, 4096, 4096, null, var6);
         if (var9 != null) {
            for (CC.Ws var11 : var9) {
               if (var11.pC == var1) {
                  var8 = ((a)this.A).pC(var1, var1 + var11.wS, null, var11.kS, var11.wS, true);
                  if (!var7) {
                     break;
                  }
               } else if (var7) {
                  ((a)this.A).pC(var1, var1 + var11.wS, null, var11.kS, var11.wS, true);
               }
            }
         }
      }

      return var8;
   }

   public avb pC(long var1, int var3, int var4, boolean var5) {
      int var6 = var3 - 1;
      int var7 = var3 - 1;
      boolean var8 = false;
      boolean var9 = false;
      if (var3 == 0) {
         var6 = 4;
         var7 = 4096;
      } else if (!var5) {
         var6 = 4;
         var7 = 4096;
         IReferenceManager var10 = this.A.getModel().getReferenceManager();

         for (IReference var13 : var10.getReferencesTo(var1)) {
            if (var13.getFrom().isInternalAddress()
               && Math.abs(var13.getFrom().getInternalAddress() - var1) < this.wS.getPageSize()
               && this.A.getModel().getItemAt(var13.getFrom().getInternalAddress()) instanceof INativeInstructionItem) {
               var7 = var3;
               var9 = true;
               break;
            }
         }

         var8 = true;
      }

      boolean var14 = DataStringUtil.isSafeAsciiStringAt(this.wS, var1, StringEncoding.ASCII_ZERO, var6, var7);
      boolean var15 = DataStringUtil.isSafeAsciiStringAt(this.wS, var1, StringEncoding.UTF16_LE_ZERO, var6 / 2, var7);
      if (var3 != var4 || var14 || var15) {
         if (var15) {
            var6 = Math.max(var6 / 2, 3);
            if (var3 != 0 && !var5) {
               var7 = 4096;
            }
         }

         if (var6 > var7) {
            return null;
         }

         if (!var9 || var15) {
            var7 += var15 ? 2 : 1;
         }

         if (DataStringUtil.isValidStringAt(this.kS, this.wS, var1, var6, var7)) {
            if (var8 && !var14 && !var15) {
               return null;
            }

            return ((a)this.A).pC(var1, var1 + var7, null, var6, var7, true);
         }
      }

      return null;
   }

   public boolean A(INativeContinuousItem var1) {
      if (var1 instanceof INativeStringItem) {
         return true;
      } else if (var1 instanceof INativeInstructionItem) {
         return false;
      } else {
         if (var1 instanceof INativeDataItem) {
            if (DataStringUtil.isSafeAsciiStringAt(this.wS, var1.getMemoryAddress(), 2, 4096)) {
               return true;
            }

            if (var1.getMemorySize() == 4L) {
               try {
                  return this.wS.readInt(var1.getMemoryAddress()) == 0;
               } catch (MemoryException var2) {
               }
            }
         }

         return false;
      }
   }

   public INativeDataItem kS(long var1, long var3) {
      int var5 = (int)(var3 - var1);
      StringEntry var6 = DataStringUtil.getStringAt(this.wS, var1, var3, 1, var5);
      if (var6 == null) {
         return null;
      } else {
         long var7 = var1 + var6.getMemorySize();

         while (true) {
            label27: {
               try {
                  if (var7 < var3) {
                     if (this.wS.readByte(var7) == 0) {
                        break label27;
                     }

                     return null;
                  }
               } catch (MemoryException var9) {
                  return null;
               }

               return ((a)this.A).pC(var1, var3, var6.getEncoding(), 1, var5, true);
            }

            var7++;
         }
      }
   }

   private static class Av {
      long pC;
      long A;
      long kS;
      StringEncoding wS;
      boolean UT;

      public Av(long var1, long var3, long var5, boolean var7) {
         this(var1, var3, var5, var7, StringEncoding.ASCII_ZERO);
      }

      public Av(long var1, long var3, long var5, boolean var7, StringEncoding var8) {
         this.pC = var1;
         this.A = var3;
         this.kS = var5;
         this.UT = var7;
         this.wS = var8;
      }

      public Av(INativeStringItem var1) {
         this.pC = var1.getMemoryAddress();
         this.A = var1.getMemoryAddressEnd();
         this.kS = var1.getMemorySize();
         this.wS = var1.getStringType();
         this.UT = true;
      }

      @Override
      public String toString() {
         return "Neighbor [address=" + Long.toHexString(this.pC) + "h, size=" + this.kS + ", defined=" + this.UT + "]";
      }
   }

   private class K {
      List pC = new ArrayList();

      public boolean pC(long var1, Long var3, Long var4) {
         long var5 = CC.this.kS(var1);
         if ((var3 == null || var5 >= var3) && (var4 == null || var5 < var4) && !this.pC.contains(var5)) {
            Long var7;
            try {
               var7 = this.pC(var5);
            } catch (MemoryException var8) {
               return true;
            }

            if (var7 != null) {
               return true;
            }

            this.pC.add(var5);
         }

         return false;
      }

      private Long pC(long var1) throws MemoryException {
         long var3 = CC.this.wS.readPointer(var1);
         if (CC.this.A.getAnalysisRanges().contains(var3)) {
            INativeContinuousItem var5 = CC.this.kS.getItemOver(var3);
            if (var5 != null) {
               return var5.getMemoryAddress() != var3 ? null : var1;
            } else {
               return var1;
            }
         } else {
            return null;
         }
      }
   }

   private static class Sv {
      List pC = new ArrayList();
      Set A = new TreeSet();
      Set kS = new TreeSet();

      public void pC(IVirtualMemory var1, StringEncoding var2) {
         if (!this.A.isEmpty()) {
            ArrayList var3 = new ArrayList();
            Iterator var4 = this.A.iterator();
            long var5 = (Long)var4.next();

            while (true) {
               long var9 = var5;
               boolean var11 = false;

               long var7;
               for (var7 = 1L; var4.hasNext(); var7++) {
                  var9 = (Long)var4.next();
                  if (var5 + var7 * 2L != var9) {
                     var11 = true;
                     break;
                  }
               }

               try {
                  byte var12 = var1.readByte(var5 + var7 * 2L);
                  byte var13 = var1.readByte(var5 + var7 * 2L + 1L);
                  if (var12 == 0 && var13 == 0) {
                     if (var7 > 1L) {
                        if (var2.getBasicCharSize() == 2) {
                           this.pC(var5, var5 + (var7 + 1L) * 2L, var7, var2);
                        }

                        for (int var14 = 0; var14 < var7; var14++) {
                           var3.add(var5 + var14 * 2);
                        }
                     } else {
                        this.kS.add(var5);
                        var3.add(var5);
                     }
                  }
               } catch (MemoryException var15) {
                  break;
               }

               if (!var4.hasNext() && !var11) {
                  break;
               }

               var5 = var9;
            }

            this.A.removeAll(var3);
         }
      }

      private void pC(long var1, long var3, long var5, StringEncoding var7) {
         this.pC.add(new CC.Av(var1, var3, var5, false, var7));
      }

      public CC.Av pC(long var1) {
         for (int var3 = 0; var3 < this.pC.size(); var3++) {
            if (((CC.Av)this.pC.get(var3)).pC == var1) {
               return (CC.Av)this.pC.remove(var3);
            }
         }

         return null;
      }

      @Override
      public String toString() {
         String var1 = this.pC.isEmpty() ? "" : (this.pC.size() > 1 ? "\n" : "") + Strings.join("\n", this.pC) + (this.pC.size() > 1 ? "\n" : "");
         String var2 = this.A.isEmpty() ? "" : Strings.join(",", this.A, Long::toHexString);
         String var3 = this.kS.isEmpty() ? "" : Strings.join(",", this.kS, Long::toHexString);
         return "NeighborContext [neighbors=" + var1 + "\nasciis=" + var2 + "\nisolatedAsciis=" + var3 + "]";
      }
   }

   private class Ws {
      long pC;
      StringEncoding A;
      int kS;
      int wS;

      public Ws(long var2, StringEncoding var4, int var5, int var6) {
         this.pC = var2;
         this.A = var4;
         this.kS = var5;
         this.wS = var6;
      }
   }

   private class bO {
      final boolean pC;
      final long A;
      final long kS;
      int wS = 0;
      int UT = 0;
      int E = 0;
      int sY = 0;
      long ys = 0L;
      long ld = 0L;
      long gp = 0L;
      Long oT = 0L;
      Long fI = 0L;

      public bO(boolean var2, long var3, long var5) {
         this.pC = var2;
         this.A = var3;
         this.kS = var5;
      }

      public void pC(CC.Sv var1, boolean var2) {
         ArrayList var3 = new ArrayList();
         var3.addAll(var1.A);
         var3.addAll(var1.kS);
         var3.addAll((Collection)var1.pC.stream().map(var0 -> var0.pC).collect(Collectors.toList()));
         if (!var3.isEmpty()) {
            Collections.sort(var3);
            if (!var2) {
               if (this.pC) {
                  this.pC(false, this.A, this.kS, (Long)var3.get(0));
               } else {
                  this.pC(null, this.A, this.kS, (Long)var3.get(0));
               }
            }

            for (int var4 = 0; var4 < var3.size() - 1; var4++) {
               Long var5 = (Long)var3.get(var4);
               Long var6 = (Long)var3.get(var4 + 1);
               CC.Av var7 = this.pC(var5, var1.pC);
               Long var8 = this.pC(var5.longValue(), var7);
               this.pC(var7, var5, var8, var6);
            }

            if (var2) {
               Long var9 = (Long)var3.get(var3.size() - 1);
               CC.Av var10 = this.pC(var9, var1.pC);
               Long var11 = this.pC(var9.longValue(), var10);
               this.pC(var10, var9, var11, this.A);
            }

            if (!var2) {
               if (this.gp == 0L) {
                  this.gp = (Long)var3.get(var3.size() - 1);
               }

               if (this.fI != null && this.fI == 0L) {
                  this.fI = (Long)var3.get(var3.size() - 1);
               }
            }
         }
      }

      private void A() {
         if (this.wS > this.UT) {
            this.UT = this.wS;
         }

         this.wS = 0;
         if (this.E > this.sY) {
            this.sY = this.E;
         }

         this.E = 0;
      }

      public boolean pC() {
         this.A();
         return this.ys <= 3L && this.UT >= 3;
      }

      public boolean pC(long var1) {
         this.A();
         return (this.UT >= 3 || this.sY >= 3) && (var1 >= this.ld && var1 <= this.gp || this.oT != null && var1 >= this.oT && var1 <= this.fI);
      }

      private CC.Av pC(Long var1, List var2) {
         for (CC.Av var4 : var2) {
            if (var4.pC == var1) {
               return var4;
            }
         }

         return null;
      }

      private Long pC(long var1, CC.Av var3) {
         return var3 != null ? var3.A : var1 + 2L;
      }

      public void pC(CC.Av var1, long var2, long var4, long var6) {
         this.pC(var1 == null || var1.kS <= 3L, var2, var4, var6);
      }

      public void pC(boolean var1, long var2, long var4, long var6) {
         if (var1) {
            this.wS++;
            if (this.E > this.sY) {
               this.sY = this.E;
            }

            this.E = 0;
         } else {
            this.E++;
            if (this.wS > this.UT) {
               this.UT = this.wS;
            }

            this.wS = 0;
         }

         long var8 = var6 - var4;
         long var10 = -1L;
         if (var2 == CC.this.kS(var2) && var6 == CC.this.kS(var6)) {
            long var12 = CC.this.kS(var4);
            var10 = var6 - (var12 + (var12 == var4 ? 0L : CC.this.pC()));
         } else {
            this.oT = null;
            this.fI = null;
         }

         if (var8 > this.ys) {
            this.ys = var8;
         }

         if (var2 < this.A) {
            if (var8 != 0L) {
               this.ld = var6;
            } else if (this.ld == 0L) {
               this.ld = var2;
            }

            if (this.oT != null) {
               if (var10 != 0L) {
                  this.oT = var6;
               } else if (this.oT == 0L) {
                  this.oT = var2;
               }
            }
         } else if (var6 > this.A) {
            if (var8 != 0L && this.gp == 0L) {
               this.gp = var4;
            }

            if (this.fI != null && var10 != 0L && this.fI == 0L) {
               this.fI = var4;
            }
         }
      }
   }
}
