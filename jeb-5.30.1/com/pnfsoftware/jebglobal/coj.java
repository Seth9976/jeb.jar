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

public class coj implements cnt {
   private static final ILogger RF = GlobalLog.getLogger(coj.class);
   private static final String xK = "MSVC RTTI memory scanner";
   private final int Dw;
   private final abg Uv;
   private final IVirtualMemory oW;
   private final cok gO;
   private int nf;
   private long gP;
   private long za;
   private long lm;
   private long zz;
   private Map JY = new HashMap();
   private Map HF = new HashMap();
   private cnr LK;

   public coj(abg var1) {
      this.Uv = var1;
      this.oW = var1.getMemory();
      this.gO = new cok(var1);
      this.Dw = this.gO.q() ? 24 : 20;
   }

   @Override
   public boolean q(boolean var1, boolean var2, boolean var3) {
      if (this.Uv.getCodeObjectContainer() instanceof com.pnfsoftware.jeb.corei.parsers.winpe.vn && this.gO() && this.nf()) {
         long var4 = this.gP;

         while (var4 < this.za - this.Dw) {
            if (this.RF(var4)) {
               cok.CU var6 = this.gO.q(var4, var1);
               if (var6 == null) {
                  UnitUtil.logError(
                     this.Uv,
                     this.Uv.getSymbolicStringAddress(var4),
                     true,
                     RF,
                     S.L("%s: error: Complete Object Locator found at %X but cannot be parsed"),
                     "MSVC RTTI memory scanner",
                     var4
                  );
                  this.nf++;
               } else {
                  this.JY.put(var4, var6);
               }

               var4 += this.Dw;
            } else {
               var4++;
            }
         }

         if (var3) {
            this.LK = this.gO.q(this.JY);
            if (this.LK == null) {
               UnitUtil.logError(this.Uv, null, true, RF, S.L("%s: cannot build type hierarchy graph"), "MSVC RTTI memory scanner");
               this.nf++;
            }
         }

         if (var2) {
            bby var17 = this.Uv.RF();
            IReferenceType var7 = var17.getVoidReference();
            IReferenceManager var8 = this.Uv.getCodeModel().getReferenceManager();
            var4 = this.gP;

            while (var4 < this.za) {
               cnv var9 = this.q(var4);
               if (var9 != null) {
                  for (long var11 : var9.xK) {
                     if (this.Uv.RF(var11) == null) {
                        this.Uv.getCodeAnalyzer().enqueuePointerForAnalysis(new CodePointer(var11), 0, 64);
                     }
                  }

                  if (var1) {
                     this.Uv.setDataAt(var4, var7, null);
                     IArrayType var18 = var17.createArray(var7, var9.xK.size());
                     if (this.gO.q(var9.RF)) {
                        String var19 = "??_7" + var9.q.substring(4) + "6B@";
                        this.Uv.q(var9.RF, var18, var19, true, true);
                     } else {
                        this.Uv.setDataAt(var9.RF, var18, null);
                     }

                     long var20 = var9.RF;

                     for (long var14 : var9.xK) {
                        var8.recordInternalReference(var20, var14, ReferenceType.PTR_DATA);
                        var20 += this.gO.q() ? 8L : 4L;
                     }
                  }

                  this.HF.put(var9.RF, var9);
                  var4 = var9.RF + var9.xK.size() * (this.gO.q() ? 8 : 4);
               } else {
                  var4 += 4L;
               }
            }

            if (this.Uv.getCodeAnalyzer().needsAnalysis()) {
               this.Uv.getCodeAnalyzer().analyze();
            }
         }

         return !this.JY.isEmpty();
      } else {
         UnitUtil.logInfo(this.Uv, null, true, RF, S.L("%s: cannot find sections to scan"), "MSVC RTTI memory scanner");
         return false;
      }
   }

   private cnv q(long var1) {
      boolean var3 = this.gO.q();

      try {
         long var6 = var3 ? this.oW.readLong(var1) : this.oW.readInt(var1) & 4294967295L;
         cok.CU var8 = (cok.CU)this.JY.get(var6);
         if (var8 == null) {
            return null;
         } else {
            long var4 = var1 + (var3 ? 8L : 4L);
            long var9 = var4;

            ArrayList var11;
            for (var11 = new ArrayList(); var4 < this.za; var4 += var3 ? 8L : 4L) {
               long var12 = var3 ? this.oW.readLong(var4) : this.oW.readInt(var4) & 4294967295L;
               if (!this.RF(var12, false) || this.JY.get(var12) != null) {
                  break;
               }

               var11.add(var12);
            }

            if (!var11.isEmpty()) {
               cnv var15 = new cnv();
               var15.RF = var9;
               var15.xK = var11;
               var15.q = var8.RF.xK();
               return var15;
            } else {
               return null;
            }
         }
      } catch (MemoryException var14) {
         return null;
      }
   }

   private boolean gO() {
      ISegmentInformation var1 = CodeObjectUnitUtil.findSegmentByName(this.Uv.getCodeObjectContainer(), ".rdata");
      if (var1 == null) {
         var1 = CodeObjectUnitUtil.findSegmentByName(this.Uv.getCodeObjectContainer(), ".text");
         if (var1 == null) {
            return false;
         }
      }

      ISegmentInformation var2 = CodeObjectUnitUtil.findSegmentByName(this.Uv.getCodeObjectContainer(), ".data");
      if (var2 == null) {
         return false;
      } else {
         this.gP = this.Uv.getVirtualImageBase() + Math.min(var1.getOffsetInMemory(), var2.getOffsetInMemory());
         this.za = this.Uv.getVirtualImageBase()
            + Math.max(var1.getOffsetInMemory() + var1.getSizeInMemory(), var2.getOffsetInMemory() + var2.getSizeInMemory());
         return this.gP < this.za;
      }
   }

   private boolean nf() {
      ISegmentInformation var1 = CodeObjectUnitUtil.findSegmentByName(this.Uv.getCodeObjectContainer(), ".text");
      if (var1 == null) {
         return false;
      } else {
         this.lm = this.Uv.getVirtualImageBase() + var1.getOffsetInMemory();
         this.zz = this.Uv.getVirtualImageBase() + var1.getOffsetInMemory() + var1.getSizeInMemory();
         return this.lm < this.zz;
      }
   }

   private boolean RF(long var1) {
      boolean var3 = this.gO.q();

      try {
         int var4 = this.oW.readInt(var1);
         if ((!var3 || var4 == 1) && (var3 || var4 == 0)) {
            long var5 = this.oW.readInt(var1 + 12L) & 4294967295L;
            if (!this.q(var5, var3)) {
               return false;
            } else {
               long var7 = this.oW.readInt(var1 + 16L) & 4294967295L;
               if (!this.q(var7, var3)) {
                  return false;
               } else {
                  long var9 = var3 ? this.oW.readLong(this.Uv.getVirtualImageBase() + var5) : this.oW.readInt(var5) & 4294967295L;
                  if (!this.q(var9, false)) {
                     return false;
                  } else {
                     int var11 = var3 ? this.oW.readInt(this.Uv.getVirtualImageBase() + var5 + 16L) : this.oW.readInt(var5 + 8L);
                     if ((var11 & 65535) != 16174) {
                        return false;
                     } else {
                        if (var3) {
                           int var12 = this.oW.readInt(var1 + 20L);
                           if (this.Uv.getVirtualImageBase() + var12 != var1) {
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

   private boolean q(long var1, boolean var3) {
      if (var3) {
         var1 += this.Uv.getVirtualImageBase();
      }

      return var1 >= this.gP && var1 < this.za;
   }

   private boolean RF(long var1, boolean var3) {
      if (var3) {
         var1 += this.Uv.getVirtualImageBase();
      }

      return var1 >= this.lm && var1 < this.zz;
   }

   @Override
   public cnr q() {
      return this.LK;
   }

   @Override
   public String RF() {
      return this.LK.q(true, true);
   }

   public Map oW() {
      return this.JY;
   }

   @Override
   public Map xK() {
      return this.HF;
   }

   @Override
   public String Dw() {
      return Strings.ff("%s: %d Complete Object Locator recovered - %d vftables recovered", "MSVC RTTI memory scanner", this.JY.size(), this.HF.size());
   }

   @Override
   public int Uv() {
      return this.nf;
   }
}
