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

public class aal {
   private static final ILogger q = GlobalLog.getLogger(aal.class);
   private static final int RF = 4;
   private static final int xK = 3;
   private static final int Dw = 4096;
   private static final int Uv = 10;
   private static final int oW = 10;
   private static final int gO = 10;
   private static final int nf = 2;
   private static final int gP = 3;
   private static final int za = 32;
   private static final int lm = 3;
   private static final int zz = 3;
   private static final int JY = 3;
   private final INativeCodeAnalyzer HF;
   private final INativeCodeModel LK;
   private final IVirtualMemory io;
   private StringEncoding qa = StringEncoding.ASCII_ZERO;

   private static boolean q(aal.CU var0) {
      if (var0.RF.isEmpty() && var0.xK.isEmpty()) {
         for (aal.eo var2 : var0.q) {
            if (!var2.Uv && var2.xK <= 3L) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private static List q(aal.CU var0, aal.CU var1) {
      ArrayList var2 = new ArrayList();
      if (var0 != null) {
         var2.addAll(var0.q);
      }

      if (var1 != null) {
         var2.addAll(var1.q);
      }

      return var2;
   }

   private static List RF(aal.CU var0, aal.CU var1) {
      ArrayList var2 = new ArrayList();
      if (var0 != null) {
         var2.addAll(var0.xK);
      }

      if (var1 != null) {
         var2.addAll(var1.xK);
      }

      return var2;
   }

   private static Couple q(List var0, long var1, long var3) {
      long var5 = var1;
      long var7 = var3;

      for (aal.eo var10 : var0) {
         if (var10.q < var5) {
            var5 = var10.q;
         }

         if (var10.RF > var7) {
            var7 = var10.RF;
         }
      }

      return new Couple(var5, var7);
   }

   public aal(INativeCodeAnalyzer var1) {
      this.HF = var1;
      this.LK = var1.getModel();
      this.io = var1.getMemory();
   }

   public axw q(long var1, long var3) {
      if (this.HF.getAnalysisRanges().contains(var1) && this.LK.getItemOver(var1) == null) {
         if (var3 == -1L) {
            var3 = this.HF.getAnalysisRanges().getLocalEnd(var1);
         } else if (Longs.compareUnsigned(var3, var1) <= 0) {
            return null;
         }

         INativeContinuousItem var5 = this.LK.getNextItem(var1);
         if (var5 != null) {
            var3 = Math.min(var3, var5.getMemoryAddress());
         }

         return this.RF(var1, var3);
      } else {
         return null;
      }
   }

   public axw RF(long var1, long var3) {
      ((aaf)this.LK).q.verifyLocked();
      INativeContinuousItem var5 = this.LK.getItemAt(var3);
      if (var5 instanceof INativeDataItem var6 && aaj.xK(var6) && TypeUtil.getNonAlias(var6.getType()) instanceof IPrimitiveType) {
         Long var7 = VirtualMemoryUtil.readAsUnsignedLongSafe(this.io, var3, (int)var5.getMemorySize());
         if (var7 != null && var7 == 0L) {
            var3++;
         }
      }

      axw var11 = ((aae)this.HF).q(var1, var3, null, 4, 4096, true);
      List var12 = null;
      if (var11 != null || DataStringUtil.isSafeAsciiStringAt(this.io, var1, 1, 4)) {
         var12 = this.q(var1, var3, 4, 4096, var11);
      }

      if (var12 != null) {
         for (aal.ej var9 : var12) {
            if (var9.q + var9.xK > var3) {
               Object[] var10000 = new Object[]{var9.q};
            } else {
               axw var10 = ((aae)this.HF).q(var9.q, var3, var9.RF, var9.xK, var9.Dw, true);
               if (var11 == null && var9.q == var1) {
                  var11 = var10;
               }
            }
         }
      }

      return var11;
   }

   private List q(long var1, long var3, int var5, int var6, axw var7) {
      if (var5 == 0) {
         return null;
      } else {
         aal.CU var8 = this.q(var1, var7, 10);
         this.q(var1, var7, var8.q);
         var8.q(this.io, this.qa);
         ArrayList var9 = new ArrayList();
         aal.nI var10 = new aal.nI();
         boolean var11 = false;
         if (var7 == null) {
            if (var8.q()) {
               return null;
            }

            if (var10.q(var1, ((aal.eo)var8.q.get(0)).RF, null)) {
               var11 = true;
            }

            boolean var12 = DataStringUtil.isSafeAsciiStringAt(this.io, var1, this.qa, 2, var5);
            if (!var12) {
               boolean var13 = DataStringUtil.isSafeAsciiStringAt(this.io, var1, 2, var5);
               if (!var13) {
                  return null;
               }
            }

            if (var8.q.size() < 10) {
               return null;
            }

            var9.add(new aal.ej(var1, var12 ? this.qa : StringEncoding.ASCII_ZERO, 2, var5));
         }

         long var26 = var7 != null ? var7.getMemoryAddressEnd() : var1;
         aal.CU var14 = this.q(var26, 10);
         var14.q(this.io, this.qa);
         long var15;
         if (var7 == null) {
            aal.eo var17 = var14.q(var1);
            if (var17 == null) {
               return null;
            }

            var15 = var17.RF;
            if (var10.q(var15 - 1L, null, null)) {
               var11 = true;
            }
         } else {
            var15 = var7.getMemoryAddressEnd();
         }

         if (var7 == null && var11 && var14.q.size() < 2) {
            return null;
         } else if (q(var8) && q(var14)) {
            return var9;
         } else {
            List var27 = q(var8, var14);
            if (var27.isEmpty()) {
               return var9;
            } else {
               List var18 = RF(var8, var14);
               aal.oM var19 = new aal.oM(var7 != null, var1, var15);
               var19.q(var8, true);
               var19.q(var14, false);
               boolean var20 = var27.size() + var18.size() >= 10;

               for (aal.eo var22 : var27) {
                  if (!var22.Uv && (var22.xK == 3L || var22.xK < 3L && (var20 || var19.q(var22.q)))) {
                     var9.add(new aal.ej(var22.q, var22.Dw, (int)var22.xK, (int)var22.xK * 2 + 2));
                  }
               }

               if (var20) {
                  ArrayList var28 = new ArrayList();
                  var28.addAll(var18);
                  boolean var29 = var19.q();
                  if (this.qa.getBasicCharSize() == 1 || var29) {
                     var28.addAll(var8.RF);
                     var28.addAll(var14.RF);
                  }

                  Couple var23 = q(var27, var1, var15);

                  for (Long var25 : var28) {
                     if (var29 || var25 > (Long)var23.getFirst() && var25 < (Long)var23.getSecond()) {
                        var9.add(new aal.ej(var25, StringEncoding.ASCII_ZERO, 1, 2));
                     }
                  }
               }

               return var9;
            }
         }
      }
   }

   private StringEncoding q(long var1, axw var3, List var4) {
      if (this.qa == StringEncoding.UTF16_LE_ZERO) {
         return this.qa;
      } else {
         this.qa = this.RF(var1, var3, var4);
         return this.qa;
      }
   }

   private StringEncoding RF(long var1, axw var3, List var4) {
      if (var3 != null && var3.getStringType().getBasicCharSize() == 2) {
         return var3.getStringType();
      } else {
         int var5 = 10;
         long var6 = var1;
         if (!var4.isEmpty()) {
            for (aal.eo var9 : var4) {
               if (var9.Uv) {
                  if (var9.Dw.getBasicCharSize() == 2) {
                     return var9.Dw;
                  }

                  var5--;
               }

               var6 = var9.q;
            }
         }

         while (var5 > 0) {
            INativeContinuousItem var13 = this.LK.getPreviousItem(var6);
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
            INativeContinuousItem var10 = this.LK.getNextItem(var14);
            if (!(var10 instanceof INativeStringItem)) {
               break;
            }

            StringEncoding var11 = ((INativeStringItem)var10).getStringType();
            if (var11.getBasicCharSize() == 2) {
               return var11;
            }

            var14 = var10.getMemoryAddress() + 1L;
         }

         return this.qa;
      }
   }

   INativeContinuousItem q(long var1) {
      int var3 = 5;

      INativeContinuousItem var4;
      for (var4 = this.LK.getPreviousItem(var1); var4 != null && this.q(var4) && var3 > 0; var3--) {
         var4 = this.LK.getPreviousItem(var4.getMemoryAddress());
      }

      return var4;
   }

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   private aal.CU q(long var1, axw var3, int var4) {
      aal.CU var5 = new aal.CU();
      List var6 = var5.q;
      aal.nI var7 = new aal.nI();
      long var8 = var1;
      int var10 = this.q((INativeStringItem)var3);

      while (var6.size() < var4) {
         int var11 = var6.size();
         INativeContinuousItem var12 = this.q(var8);
         var10 = Math.max(var10, this.xK(var12));
         int var13 = 3;
         boolean var14 = false;
         long var15 = 0L;

         boolean var17;
         for (var17 = false; var13 > 0; var12 = this.q(var12.getMemoryAddress())) {
            var14 = false;
            var15 = var12 != null ? var12.getMemoryAddressEnd() : 0L;
            var17 = false;
            if (var12 instanceof INativeStringItem) {
               if (var12.getMemoryAddressEnd() == var8) {
                  var6.add(new aal.eo((INativeStringItem)var12));
                  break;
               }

               if (var8 - var12.getMemoryAddressEnd() >= var10) {
                  var14 = true;
                  break;
               }

               try {
                  for (long var34 = var12.getMemoryAddressEnd(); var34 < var8; var34++) {
                     if (this.io.readByte(var34) != 0) {
                        var14 = true;
                        break;
                     }
                  }

                  if (!var14) {
                     var6.add(new aal.eo((INativeStringItem)var12));
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
               } else if (var12 instanceof axv && var12.getName(true).startsWith("gvar_") && !var7.q(var12.getMemoryAddress(), null, var1)) {
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
                  var20 = this.io.readByte(var35);
                  if (var20 != 0) {
                     break;
                  }

                  if (var7.q(var35, null, var1)) {
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
                     if (this.q(var20) && (var12 == null || var35 >= var15) && var25 < 4096) {
                        if (!var7.q(var35, null, var1)) {
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
                           var6.add(new aal.eo(var35 + 1L, var22 + 1L, var25, false));
                        } else {
                           if (!var5.RF.contains(var35 + 3L) && var5.RF.size() >= 30) {
                              return var5;
                           }

                           var5.RF.add(var35 + 1L);
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
                  var20 = this.io.readByte(var35);
               } catch (MemoryException var26) {
                  return var5;
               }
            }
         }

         if (var6.size() == var11) {
            var8 = var35 + 1L;
         } else {
            var8 = ((aal.eo)var6.get(var6.size() - 1)).q;
         }
      }

      return var5;
   }

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   private aal.CU q(long var1, int var3) {
      aal.CU var4 = new aal.CU();
      List var5 = var4.q;
      aal.nI var6 = new aal.nI();
      long var7 = var1;
      int var9 = this.q(this.qa);

      while (var5.size() < var3) {
         int var10 = var5.size();
         INativeContinuousItem var11 = this.LK.getNextItem(var7 - 1L);
         boolean var12 = false;
         long var13 = var11 != null ? var11.getMemoryAddress() : Long.MAX_VALUE;
         if (var11 instanceof INativeStringItem) {
            if (var11.getMemoryAddress() == var7) {
               var5.add(new aal.eo((INativeStringItem)var11));
            } else if (var11.getMemoryAddress() - var7 >= var9) {
               var12 = true;
            } else {
               try {
                  for (long var30 = var7; var30 < var11.getMemoryAddress(); var30++) {
                     if (this.io.readByte(var30) != 0) {
                        var12 = true;
                        break;
                     }
                  }

                  if (!var12) {
                     var5.add(new aal.eo((INativeStringItem)var11));
                  }
               } catch (MemoryException var26) {
                  return var4;
               }
            }
         } else {
            if (var11 instanceof INativeDataItem && var11.getMemorySize() == 1L && "unsigned char".equals(((INativeDataItem)var11).getType().getSignature())) {
               INativeContinuousItem var15 = this.LK.getNextItem(var11.getMemoryAddress());
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
                  var17 = this.io.readByte(var31);
                  if (var17 != 0) {
                     break;
                  }

                  if (var6.q(var31, var1, null)) {
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
               label168: {
                  try {
                     if (this.q(var17) && (var11 == null || var31 < var13) && var21 < 4096) {
                        if (!var6.q(var31, var1, null)) {
                           break label168;
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
                        var5.add(new aal.eo(var19, var31 + 1L, var21, false));
                     } else {
                        if (!var4.RF.contains(var19 - 2L) && var4.RF.size() >= 30) {
                           return var4;
                        }

                        var4.RF.add(var19);
                     }
                     break;
                  } catch (MemoryException var25) {
                     return var4;
                  }
               }

               try {
                  var31++;
                  var21++;
                  var17 = this.io.readByte(var31);
               } catch (MemoryException var22) {
                  return var4;
               }
            }
         }

         if (var5.size() == var10) {
            var7 = var31;
         } else {
            var7 = ((aal.eo)var5.get(var5.size() - 1)).RF;
         }
      }

      return var4;
   }

   private long RF(long var1) {
      return this.io.getSpaceBits() == 64 ? var1 & -8L : var1 & 4294967292L;
   }

   private long q() {
      return this.io.getSpaceBits() == 64 ? 8L : 4L;
   }

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   public boolean q(INativeContinuousItem var1) {
      byte[] var2 = new byte[(int)var1.getMemorySize()];

      byte[] var3;
      int var4;
      int var5;
      try {
         this.io.read(var1.getMemoryAddress(), var2.length, var2, 0);
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

   private int xK(INativeContinuousItem var1) {
      return var1 instanceof INativeStringItem ? this.q((INativeStringItem)var1) : this.RF();
   }

   private int q(INativeStringItem var1) {
      return var1 != null ? this.q(var1.getStringType()) : this.RF();
   }

   private int q(StringEncoding var1) {
      return 32 + (var1.getBasicCharSize() == 1 ? 1 : 3);
   }

   private int RF() {
      return 33;
   }

   private boolean q(byte var1) {
      return var1 >= 32 && var1 <= 126 || var1 == 13 || var1 == 10 || var1 == 9;
   }

   public axw q(long var1, int var3, int var4, boolean var5) {
      axw var6 = this.RF(var1, var3, var4, var5);
      if (var6 == null && DataStringUtil.isSafeAsciiStringAt(this.io, var1, 1, 4096)) {
         List var7 = this.q(var1, var1, 4096, 4096, null);
         if (var7 != null) {
            for (aal.ej var9 : var7) {
               if (var9.q == var1) {
                  return ((aae)this.HF).q(var1, var1 + var9.Dw, null, var9.xK, var9.Dw, false);
               }
            }
         }
      }

      return var6;
   }

   public axw RF(long var1, int var3, int var4, boolean var5) {
      int var6 = var3 - 1;
      int var7 = var3 - 1;
      boolean var8 = false;
      if (var3 == 0) {
         var6 = 4;
         var7 = 4096;
      } else if (!var5) {
         var6 = 4;
         var7 = 4096;
         IReferenceManager var9 = this.HF.getModel().getReferenceManager();

         for (IReference var12 : var9.getReferencesTo(var1)) {
            if (var12.getFrom().isInternalAddress()
               && Math.abs(var12.getFrom().getInternalAddress() - var1) < this.io.getPageSize()
               && this.HF.getModel().getItemAt(var12.getFrom().getInternalAddress()) instanceof INativeInstructionItem) {
               var7 = var3;
               break;
            }
         }

         var8 = true;
      }

      boolean var14 = DataStringUtil.isSafeAsciiStringAt(this.io, var1, StringEncoding.ASCII_ZERO, var6, var7);
      boolean var15 = DataStringUtil.isSafeAsciiStringAt(this.io, var1, StringEncoding.UTF16_LE_ZERO, var6 / 2, var7);
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

         var7 += var15 ? 2 : 1;
         if (DataStringUtil.isValidStringAt(this.LK, this.io, var1, var6, var7)) {
            if (var8 && !var14 && !var15) {
               return null;
            }

            return ((aae)this.HF).q(var1, var1 + var7, null, var6, var7, true);
         }
      }

      return null;
   }

   public boolean RF(INativeContinuousItem var1) {
      if (var1 instanceof INativeStringItem) {
         return true;
      } else if (var1 instanceof INativeInstructionItem) {
         return false;
      } else {
         if (var1 instanceof INativeDataItem) {
            if (DataStringUtil.isSafeAsciiStringAt(this.io, var1.getMemoryAddress(), 2, 4096)) {
               return true;
            }

            if (var1.getMemorySize() == 4L) {
               try {
                  return this.io.readInt(var1.getMemoryAddress()) == 0;
               } catch (MemoryException var2) {
               }
            }
         }

         return false;
      }
   }

   private static class CU {
      List q = new ArrayList();
      Set RF = new TreeSet();
      Set xK = new TreeSet();

      public boolean q() {
         return this.q.isEmpty();
      }

      public void q(IVirtualMemory var1, StringEncoding var2) {
         if (!this.RF.isEmpty()) {
            ArrayList var3 = new ArrayList();
            Iterator var4 = this.RF.iterator();
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
                           this.q(var5, var5 + (var7 + 1L) * 2L, var7, var2);
                        }

                        for (int var14 = 0; var14 < var7; var14++) {
                           var3.add(var5 + var14 * 2);
                        }
                     } else {
                        this.xK.add(var5);
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

            this.RF.removeAll(var3);
         }
      }

      private void q(long var1, long var3, long var5, StringEncoding var7) {
         this.q.add(new aal.eo(var1, var3, var5, false, var7));
      }

      public aal.eo q(long var1) {
         for (int var3 = 0; var3 < this.q.size(); var3++) {
            if (((aal.eo)this.q.get(var3)).q == var1) {
               return (aal.eo)this.q.remove(var3);
            }
         }

         return null;
      }

      @Override
      public String toString() {
         String var1 = this.q.isEmpty() ? "" : (this.q.size() > 1 ? "\n" : "") + Strings.join("\n", this.q) + (this.q.size() > 1 ? "\n" : "");
         String var2 = this.RF.isEmpty() ? "" : Strings.join(",", this.RF, Long::toHexString);
         String var3 = this.xK.isEmpty() ? "" : Strings.join(",", this.xK, Long::toHexString);
         return "NeighborContext [neighbors=" + var1 + "\nasciis=" + var2 + "\nisolatedAsciis=" + var3 + "]";
      }
   }

   private class ej {
      long q;
      StringEncoding RF;
      int xK;
      int Dw;

      public ej(long var2, StringEncoding var4, int var5, int var6) {
         this.q = var2;
         this.RF = var4;
         this.xK = var5;
         this.Dw = var6;
      }
   }

   private static class eo {
      long q;
      long RF;
      long xK;
      StringEncoding Dw;
      boolean Uv;

      public eo(long var1, long var3, long var5, boolean var7) {
         this(var1, var3, var5, var7, StringEncoding.ASCII_ZERO);
      }

      public eo(long var1, long var3, long var5, boolean var7, StringEncoding var8) {
         this.q = var1;
         this.RF = var3;
         this.xK = var5;
         this.Uv = var7;
         this.Dw = var8;
      }

      public eo(INativeStringItem var1) {
         this.q = var1.getMemoryAddress();
         this.RF = var1.getMemoryAddressEnd();
         this.xK = var1.getMemorySize();
         this.Dw = var1.getStringType();
         this.Uv = true;
      }

      @Override
      public String toString() {
         return "Neighbor [address=" + Long.toHexString(this.q) + "h, size=" + this.xK + ", defined=" + this.Uv + "]";
      }
   }

   private class nI {
      List q = new ArrayList();

      public boolean q(long var1, Long var3, Long var4) {
         long var5 = aal.this.RF(var1);
         if ((var3 == null || var5 >= var3) && (var4 == null || var5 < var4) && !this.q.contains(var5)) {
            Long var7;
            try {
               var7 = this.q(var5);
            } catch (MemoryException var8) {
               return true;
            }

            if (var7 != null) {
               return true;
            }

            this.q.add(var5);
         }

         return false;
      }

      private Long q(long var1) throws MemoryException {
         long var3 = aal.this.io.readPointer(var1);
         if (aal.this.HF.getAnalysisRanges().contains(var3)) {
            INativeContinuousItem var5 = aal.this.LK.getItemOver(var3);
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

   private class oM {
      final boolean q;
      final long RF;
      final long xK;
      int Dw = 0;
      int Uv = 0;
      int oW = 0;
      int gO = 0;
      long nf = 0L;
      long gP = 0L;
      long za = 0L;
      Long lm = 0L;
      Long zz = 0L;

      public oM(boolean var2, long var3, long var5) {
         this.q = var2;
         this.RF = var3;
         this.xK = var5;
      }

      public void q(aal.CU var1, boolean var2) {
         ArrayList var3 = new ArrayList();
         var3.addAll(var1.RF);
         var3.addAll(var1.xK);
         var3.addAll((Collection)var1.q.stream().map(var0 -> var0.q).collect(Collectors.toList()));
         if (!var3.isEmpty()) {
            Collections.sort(var3);
            if (!var2) {
               if (this.q) {
                  this.q(false, this.RF, this.xK, (Long)var3.get(0));
               } else {
                  this.q(null, this.RF, this.xK, (Long)var3.get(0));
               }
            }

            for (int var4 = 0; var4 < var3.size() - 1; var4++) {
               Long var5 = (Long)var3.get(var4);
               Long var6 = (Long)var3.get(var4 + 1);
               aal.eo var7 = this.q(var5, var1.q);
               Long var8 = this.q(var5.longValue(), var7);
               this.q(var7, var5, var8, var6);
            }

            if (var2) {
               Long var9 = (Long)var3.get(var3.size() - 1);
               aal.eo var10 = this.q(var9, var1.q);
               Long var11 = this.q(var9.longValue(), var10);
               this.q(var10, var9, var11, this.RF);
            }

            if (!var2) {
               if (this.za == 0L) {
                  this.za = (Long)var3.get(var3.size() - 1);
               }

               if (this.zz != null && this.zz == 0L) {
                  this.zz = (Long)var3.get(var3.size() - 1);
               }
            }
         }
      }

      private void RF() {
         if (this.Dw > this.Uv) {
            this.Uv = this.Dw;
         }

         this.Dw = 0;
         if (this.oW > this.gO) {
            this.gO = this.oW;
         }

         this.oW = 0;
      }

      public boolean q() {
         this.RF();
         return this.nf <= 3L && this.Uv >= 3;
      }

      public boolean q(long var1) {
         this.RF();
         return (this.Uv >= 3 || this.gO >= 3) && (var1 >= this.gP && var1 <= this.za || this.lm != null && var1 >= this.lm && var1 <= this.zz);
      }

      private aal.eo q(Long var1, List var2) {
         for (aal.eo var4 : var2) {
            if (var4.q == var1) {
               return var4;
            }
         }

         return null;
      }

      private Long q(long var1, aal.eo var3) {
         return var3 != null ? var3.RF : var1 + 2L;
      }

      public void q(aal.eo var1, long var2, long var4, long var6) {
         this.q(var1 == null || var1.xK <= 3L, var2, var4, var6);
      }

      public void q(boolean var1, long var2, long var4, long var6) {
         if (var1) {
            this.Dw++;
            if (this.oW > this.gO) {
               this.gO = this.oW;
            }

            this.oW = 0;
         } else {
            this.oW++;
            if (this.Dw > this.Uv) {
               this.Uv = this.Dw;
            }

            this.Dw = 0;
         }

         long var8 = var6 - var4;
         long var10 = -1L;
         if (var2 == aal.this.RF(var2) && var6 == aal.this.RF(var6)) {
            long var12 = aal.this.RF(var4);
            var10 = var6 - (var12 + (var12 == var4 ? 0L : aal.this.q()));
         } else {
            this.lm = null;
            this.zz = null;
         }

         if (var8 > this.nf) {
            this.nf = var8;
         }

         if (var2 < this.RF) {
            if (var8 != 0L) {
               this.gP = var6;
            } else if (this.gP == 0L) {
               this.gP = var2;
            }

            if (this.lm != null) {
               if (var10 != 0L) {
                  this.lm = var6;
               } else if (this.lm == 0L) {
                  this.lm = var2;
               }
            }
         } else if (var6 > this.RF) {
            if (var8 != 0L && this.za == 0L) {
               this.za = var4;
            }

            if (this.zz != null && var10 != 0L && this.zz == 0L) {
               this.zz = var4;
            }
         }
      }
   }
}
