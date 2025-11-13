package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReferenceManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.IArrayType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class chf implements cgp {
   private static final ILogger pC = GlobalLog.getLogger(chf.class);
   private final int A;
   private final C kS;
   private final IVirtualMemory wS;
   private final chg UT;
   private int E;
   private long sY;
   private long ys;
   private long ld;
   private long gp;
   private Map oT = new HashMap();
   private Map fI = new HashMap();
   private cgn WR;

   public chf(C var1) {
      this.kS = var1;
      this.wS = var1.getMemory();
      this.UT = new chg(var1);
      this.A = this.UT.pC() ? 24 : 20;
   }

   @Override
   public boolean pC(boolean var1, boolean var2, boolean var3) {
      if (this.kS.getCodeObjectContainer() instanceof com.pnfsoftware.jeb.corei.parsers.winpe.yt && this.UT() && this.E()) {
         long var4 = this.sY;

         while (var4 < this.ys - this.A) {
            if (this.A(var4)) {
               chg.Sv var6 = this.UT.pC(var4, var1);
               if (var6 == null) {
                  UnitUtil.logError(
                     this.kS,
                     this.kS.getSymbolicStringAddress(var4),
                     true,
                     pC,
                     S.L("%s: error: Complete Object Locator found at %X but cannot be parsed"),
                     "MSVC RTTI memory scanner",
                     var4
                  );
                  this.E++;
               } else {
                  this.oT.put(var4, var6);
               }

               var4 += this.A;
            } else {
               var4++;
            }
         }

         if (var3) {
            this.WR = this.UT.pC(this.oT);
            if (this.WR == null) {
               UnitUtil.logError(this.kS, null, true, pC, S.L("%s: cannot build type hierarchy graph"), "MSVC RTTI memory scanner");
               this.E++;
            }
         }

         if (var2) {
            ayy var17 = this.kS.A();
            IReferenceType var7 = var17.getVoidReference();
            IReferenceManager var8 = this.kS.getCodeModel().getReferenceManager();
            var4 = this.sY;

            while (var4 < this.ys) {
               cgr var9 = this.pC(var4);
               if (var9 != null) {
                  for (long var11 : var9.kS) {
                     if (this.kS.A(var11) == null) {
                        this.kS.getCodeAnalyzer().enqueuePointerForAnalysis(new CodePointer(var11), 0, 64);
                     }
                  }

                  if (var1) {
                     this.kS.setDataAt(var4, var7, null);
                     IArrayType var18 = var17.createArray(var7, var9.kS.size());
                     if (this.UT.pC(var9.A)) {
                        String var19 = "??_7" + var9.pC.substring(4) + "6B@";
                        this.kS.pC(var9.A, var18, var19, true, true);
                     } else {
                        this.kS.setDataAt(var9.A, var18, null);
                     }

                     long var20 = var9.A;

                     for (long var14 : var9.kS) {
                        var8.recordInternalReference(var20, var14, ReferenceType.PTR_DATA);
                        var20 += this.UT.pC() ? 8L : 4L;
                     }
                  }

                  this.fI.put(var9.A, var9);
                  var4 = var9.A + var9.kS.size() * (this.UT.pC() ? 8 : 4);
               } else {
                  var4 += 4L;
               }
            }

            if (this.kS.getCodeAnalyzer().needsAnalysis()) {
               this.kS.getCodeAnalyzer().analyze();
            }
         }

         return !this.oT.isEmpty();
      } else {
         UnitUtil.logInfo(this.kS, null, true, pC, S.L("%s: cannot find sections to scan"), "MSVC RTTI memory scanner");
         return false;
      }
   }

   private cgr pC(long var1) {
      boolean var3 = this.UT.pC();

      try {
         long var6 = var3 ? this.wS.readLong(var1) : this.wS.readInt(var1) & 4294967295L;
         chg.Sv var8 = (chg.Sv)this.oT.get(var6);
         if (var8 == null) {
            return null;
         } else {
            long var4 = var1 + (var3 ? 8L : 4L);
            long var9 = var4;

            ArrayList var11;
            for (var11 = new ArrayList(); var4 < this.ys; var4 += var3 ? 8L : 4L) {
               long var12 = var3 ? this.wS.readLong(var4) : this.wS.readInt(var4) & 4294967295L;
               if (!this.A(var12, false) || this.oT.get(var12) != null) {
                  break;
               }

               var11.add(var12);
            }

            if (!var11.isEmpty()) {
               cgr var15 = new cgr();
               var15.A = var9;
               var15.kS = var11;
               var15.pC = var8.A.kS();
               return var15;
            } else {
               return null;
            }
         }
      } catch (MemoryException var14) {
         return null;
      }
   }

   private boolean UT() {
      ISegmentInformation var1 = CodeObjectUnitUtil.findSegmentByName(this.kS.getCodeObjectContainer(), ".rdata");
      if (var1 == null) {
         var1 = CodeObjectUnitUtil.findSegmentByName(this.kS.getCodeObjectContainer(), ".text");
         if (var1 == null) {
            return false;
         }
      }

      ISegmentInformation var2 = CodeObjectUnitUtil.findSegmentByName(this.kS.getCodeObjectContainer(), ".data");
      if (var2 == null) {
         return false;
      } else {
         this.sY = this.kS.getVirtualImageBase() + Math.min(var1.getOffsetInMemory(), var2.getOffsetInMemory());
         this.ys = this.kS.getVirtualImageBase()
            + Math.max(var1.getOffsetInMemory() + var1.getSizeInMemory(), var2.getOffsetInMemory() + var2.getSizeInMemory());
         return this.sY < this.ys;
      }
   }

   private boolean E() {
      ISegmentInformation var1 = CodeObjectUnitUtil.findSegmentByName(this.kS.getCodeObjectContainer(), ".text");
      if (var1 == null) {
         return false;
      } else {
         this.ld = this.kS.getVirtualImageBase() + var1.getOffsetInMemory();
         this.gp = this.kS.getVirtualImageBase() + var1.getOffsetInMemory() + var1.getSizeInMemory();
         return this.ld < this.gp;
      }
   }

   private boolean A(long var1) {
      boolean var3 = this.UT.pC();

      try {
         int var4 = this.wS.readInt(var1);
         if ((!var3 || var4 == 1) && (var3 || var4 == 0)) {
            long var5 = this.wS.readInt(var1 + 12L) & 4294967295L;
            if (!this.pC(var5, var3)) {
               return false;
            } else {
               long var7 = this.wS.readInt(var1 + 16L) & 4294967295L;
               if (!this.pC(var7, var3)) {
                  return false;
               } else {
                  long var9 = var3 ? this.wS.readLong(this.kS.getVirtualImageBase() + var5) : this.wS.readInt(var5) & 4294967295L;
                  if (!this.pC(var9, false)) {
                     return false;
                  } else {
                     int var11 = var3 ? this.wS.readInt(this.kS.getVirtualImageBase() + var5 + 16L) : this.wS.readInt(var5 + 8L);
                     if ((var11 & 65535) != 16174) {
                        return false;
                     } else {
                        if (var3) {
                           int var12 = this.wS.readInt(var1 + 20L);
                           if (this.kS.getVirtualImageBase() + var12 != var1) {
                              return false;
                           }
                        }

                        return true;
                     }
                  }
               }
            }
         } else {
            return false;
         }
      } catch (MemoryException var13) {
         return false;
      }
   }

   private boolean pC(long var1, boolean var3) {
      if (var3) {
         var1 += this.kS.getVirtualImageBase();
      }

      return var1 >= this.sY && var1 < this.ys;
   }

   private boolean A(long var1, boolean var3) {
      if (var3) {
         var1 += this.kS.getVirtualImageBase();
      }

      return var1 >= this.ld && var1 < this.gp;
   }

   @Override
   public cgn pC() {
      return this.WR;
   }

   @Override
   public String A() {
      return this.WR.pC(true, true);
   }

   @Override
   public String kS() {
      return Strings.ff("%s: %d Complete Object Locator recovered - %d vftables recovered", "MSVC RTTI memory scanner", this.oT.size(), this.fI.size());
   }

   @Override
   public int wS() {
      return this.E;
   }
}
