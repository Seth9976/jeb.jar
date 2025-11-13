package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReferenceManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledData;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.IArrayType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.core.units.codeobject.ISymbolInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class cgu implements cgp {
   private static final ILogger pC = GlobalLog.getLogger(cgu.class);
   private final C A;
   private final IVirtualMemory kS;
   private final cgv wS;
   private Map UT = new HashMap();
   private Map E = new HashMap();
   private Map sY = new HashMap();
   private cgn ys;
   private long ld;
   private long gp;
   private long oT;
   private long fI;
   private int WR;

   public cgu(C var1) {
      this.A = var1;
      this.kS = var1.getMemory();
      this.wS = new cgv(var1);
   }

   @Override
   public boolean pC(boolean var1, boolean var2, boolean var3) {
      if (this.A.getCodeObjectContainer() instanceof IELFUnit && this.UT() && this.E()) {
         HashMap var4 = new HashMap();

         for (cgv.Av var8 : cgv.Av.values()) {
            ISymbolInformation var9 = CodeObjectUnitUtil.findSymbolByName(this.A.getCodeObjectContainer(), var8.wS);
            if (var9 != null && var9.getSymbolRelativeAddress() != 0L) {
               long var10 = var9.getSymbolRelativeAddress() + this.A.getVirtualImageBase();
               var4.put(var10 + (this.wS.pC() ? 16 : 8), var8);
            }
         }

         if (!var4.isEmpty()) {
            for (long var18 = this.ld; var18 < this.gp; var18 += 4L) {
               try {
                  long var25 = this.wS.pC() ? this.kS.readLong(var18) : this.kS.readInt(var18) & 4294967295L;
                  cgv.Av var32 = (cgv.Av)var4.get(var25);
                  if (var32 != null) {
                     this.UT.put(var18, var32);
                  }
               } catch (MemoryException var17) {
               }
            }
         }

         if (this.UT.isEmpty()) {
            for (cgv.Av var29 : cgv.Av.values()) {
               for (ISymbolInformation var11 : CodeObjectUnitUtil.findAllSymbolsByName(this.A.getCodeObjectContainer(), var29.wS)) {
                  if (var11.getType() == SymbolType.PTROBJECT) {
                     long var12 = var11.getSymbolRelativeAddress() + this.A.getVirtualImageBase();
                     this.UT.put(var12, var29);
                  }
               }
            }
         }

         if (this.UT.isEmpty()) {
            return false;
         } else {
            for (Entry var23 : this.UT.entrySet()) {
               cgt var27 = ((cgv.Av)var23.getValue()).pC((Long)var23.getKey(), this.wS);
               if (var27 != null) {
                  this.E.put((Long)var23.getKey(), var27);
                  if (var27 instanceof cgz && !this.UT.containsKey(((cgz)var27).UT)) {
                     UnitUtil.logError(
                        this.A,
                        this.A.getSymbolicStringAddress((Long)var23.getKey()),
                        true,
                        pC,
                        S.L("%s: not parsed base type_info at %X"),
                        "Itanium RTTI memory scanner",
                        var23.getKey()
                     );
                     this.WR++;
                  }

                  if (var27 instanceof cha) {
                     for (cgs var34 : ((cha)var27).ys) {
                        if (!this.UT.containsKey(var34.pC)) {
                           UnitUtil.logError(
                              this.A,
                              this.A.getSymbolicStringAddress((Long)var23.getKey()),
                              true,
                              pC,
                              S.L("%s: not parsed base type_info at %X"),
                              "Itanium RTTI memory scanner",
                              var34.pC
                           );
                           this.WR++;
                        }
                     }
                  }

                  if (var1) {
                     this.wS.pC((Long)var23.getKey(), var27, true);
                  }
               } else {
                  UnitUtil.logError(
                     this.A,
                     this.A.getSymbolicStringAddress((Long)var23.getKey()),
                     true,
                     pC,
                     S.L("%s: cannot parse type_info at %X"),
                     "Itanium RTTI memory scanner",
                     var23.getKey()
                  );
                  this.WR++;
               }
            }

            if (var3) {
               this.ys = this.wS.pC(this.E);
               if (this.ys == null) {
                  UnitUtil.logError(this.A, null, true, pC, S.L("%s: cannot build type hierarchy graph"), "Itanium RTTI memory scanner");
               }
            }

            if (var2) {
               ayy var21 = this.A.A();
               IReferenceType var24 = var21.getVoidReference();
               IReferenceManager var28 = this.A.getCodeModel().getReferenceManager();
               long var31 = this.ld;

               while (var31 < this.gp) {
                  cgr var36 = this.pC(var31);
                  if (var36 != null) {
                     if (var1) {
                        this.A.setDataAt(var31, var24, null);
                        IArrayType var37 = var21.createArray(var24, var36.kS.size());
                        if (this.wS.pC(var36.A)) {
                           IUnmangledData var39 = this.A.getCodeAnalyzer().getUnmanglerService().unmangle("_Z" + var36.pC, true);
                           if (var39 != null) {
                              var36.pC = var39.getFull();
                           }

                           String var13 = "vftable_" + var36.pC;
                           this.A.pC(var36.A, var37, var13, true, true);
                        } else {
                           this.A.setDataAt(var36.A, var37, null);
                        }

                        long var40 = var36.A;

                        for (long var15 : var36.kS) {
                           var28.recordInternalReference(var40, var15, ReferenceType.PTR_DATA);
                           var40 += this.wS.pC() ? 8L : 4L;
                        }
                     }

                     for (long var41 : var36.kS) {
                        CodePointer var42 = this.A.getProcessor().createEntryPoint(var41);
                        if (this.A.A(var42.getAddress()) == null) {
                           this.A.getCodeAnalyzer().enqueuePointerForAnalysis(var42, 0, 64);
                        }
                     }

                     this.sY.put(var36.A, var36);
                     var31 = var36.A + var36.kS.size() * (this.wS.pC() ? 8 : 4);
                  } else {
                     var31 += 4L;
                  }
               }
            }

            if (this.A.getCodeAnalyzer().needsAnalysis()) {
               this.A.getCodeAnalyzer().analyze();
            }

            return !this.UT.isEmpty();
         }
      } else {
         UnitUtil.logTrace(this.A, null, pC, S.L("%s: cannot find sections to scan"), "Itanium RTTI memory scanner");
         return false;
      }
   }

   private boolean UT() {
      ISegmentInformation var1 = CodeObjectUnitUtil.findSectionByName(this.A.getCodeObjectContainer(), ".rodata");
      if (var1 == null) {
         return false;
      } else {
         ISegmentInformation var2 = CodeObjectUnitUtil.findSectionByName(this.A.getCodeObjectContainer(), ".data.rel.ro");
         if (var2 == null) {
            return false;
         } else {
            this.ld = this.A.getVirtualImageBase() + Math.min(var1.getOffsetInMemory(), var2.getOffsetInMemory());
            this.gp = this.A.getVirtualImageBase()
               + Math.max(var1.getOffsetInMemory() + var1.getSizeInMemory(), var2.getOffsetInMemory() + var2.getSizeInMemory());
            return this.ld < this.gp;
         }
      }
   }

   private boolean E() {
      ISegmentInformation var1 = CodeObjectUnitUtil.findSectionByName(this.A.getCodeObjectContainer(), ".text");
      if (var1 == null) {
         return false;
      } else {
         this.oT = this.A.getVirtualImageBase() + var1.getOffsetInMemory();
         this.fI = this.A.getVirtualImageBase() + var1.getOffsetInMemory() + var1.getSizeInMemory();
         return this.oT < this.fI;
      }
   }

   private cgr pC(long var1) {
      boolean var3 = this.wS.pC();

      try {
         long var6 = var3 ? this.kS.readLong(var1) : this.kS.readInt(var1) & 4294967295L;
         cgt var8 = (cgt)this.E.get(var6);
         if (var8 == null) {
            return null;
         } else {
            long var4 = var1 + (var3 ? 8L : 4L);
            long var9 = var4;

            ArrayList var11;
            for (var11 = new ArrayList(); var4 < this.gp; var4 += var3 ? 8L : 4L) {
               long var12 = var3 ? this.kS.readLong(var4) : this.kS.readInt(var4) & 4294967295L;
               if (!this.pC(var12, false)) {
                  break;
               }

               var11.add(var12);
            }

            if (!var11.isEmpty()) {
               cgr var15 = new cgr();
               var15.pC = var8.pC();
               var15.A = var9;
               var15.kS = var11;
               return var15;
            } else {
               return null;
            }
         }
      } catch (MemoryException var14) {
         return null;
      }
   }

   private boolean pC(long var1, boolean var3) {
      if (var3) {
         var1 += this.A.getVirtualImageBase();
      }

      return var1 >= this.oT && var1 < this.fI;
   }

   @Override
   public cgn pC() {
      return this.ys;
   }

   @Override
   public String A() {
      return this.ys.pC(true, true);
   }

   @Override
   public String kS() {
      return Strings.ff("%s: %d type_infos parsed - %d vftables recovered", "Itanium RTTI memory scanner", this.E.size(), this.sY.size());
   }

   @Override
   public int wS() {
      return this.WR;
   }
}
