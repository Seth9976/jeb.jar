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

public class cny implements cnt {
   private static final ILogger RF = GlobalLog.getLogger(cny.class);
   private static final String xK = "Itanium RTTI memory scanner";
   private final abg Dw;
   private final IVirtualMemory Uv;
   private final cnz oW;
   private Map gO = new HashMap();
   private Map nf = new HashMap();
   private Map gP = new HashMap();
   private cnr za;
   private long lm;
   private long zz;
   private long JY;
   private long HF;
   private int LK;

   public cny(abg var1) {
      this.Dw = var1;
      this.Uv = var1.getMemory();
      this.oW = new cnz(var1);
   }

   @Override
   public boolean q(boolean var1, boolean var2, boolean var3) {
      if (this.Dw.getCodeObjectContainer() instanceof IELFUnit && this.gO() && this.nf()) {
         HashMap var4 = new HashMap();

         for (cnz.eo var8 : cnz.eo.values()) {
            ISymbolInformation var9 = CodeObjectUnitUtil.findSymbolByName(this.Dw.getCodeObjectContainer(), var8.Dw);
            if (var9 != null && var9.getSymbolRelativeAddress() != 0L) {
               long var10 = var9.getSymbolRelativeAddress() + this.Dw.getVirtualImageBase();
               var4.put(var10 + (this.oW.q() ? 16 : 8), var8);
            }
         }

         if (!var4.isEmpty()) {
            for (long var18 = this.lm; var18 < this.zz; var18 += 4L) {
               try {
                  long var25 = this.oW.q() ? this.Uv.readLong(var18) : this.Uv.readInt(var18) & 4294967295L;
                  cnz.eo var32 = (cnz.eo)var4.get(var25);
                  if (var32 != null) {
                     this.gO.put(var18, var32);
                  }
               } catch (MemoryException var17) {
               }
            }
         }

         if (this.gO.isEmpty()) {
            for (cnz.eo var29 : cnz.eo.values()) {
               for (ISymbolInformation var11 : CodeObjectUnitUtil.findAllSymbolsByName(this.Dw.getCodeObjectContainer(), var29.Dw)) {
                  if (var11.getType() == SymbolType.PTROBJECT) {
                     long var12 = var11.getSymbolRelativeAddress() + this.Dw.getVirtualImageBase();
                     this.gO.put(var12, var29);
                  }
               }
            }
         }

         if (this.gO.isEmpty()) {
            return false;
         } else {
            for (Entry var23 : this.gO.entrySet()) {
               cnx var27 = ((cnz.eo)var23.getValue()).q((Long)var23.getKey(), this.oW);
               if (var27 != null) {
                  this.nf.put((Long)var23.getKey(), var27);
                  if (var27 instanceof cod && !this.gO.containsKey(((cod)var27).gO)) {
                     UnitUtil.logError(
                        this.Dw,
                        this.Dw.getSymbolicStringAddress((Long)var23.getKey()),
                        true,
                        RF,
                        S.L("%s: not parsed base type_info at %X"),
                        "Itanium RTTI memory scanner",
                        var23.getKey()
                     );
                     this.LK++;
                  }

                  if (var27 instanceof coe) {
                     for (cnw var34 : ((coe)var27).za) {
                        if (!this.gO.containsKey(var34.RF)) {
                           UnitUtil.logError(
                              this.Dw,
                              this.Dw.getSymbolicStringAddress((Long)var23.getKey()),
                              true,
                              RF,
                              S.L("%s: not parsed base type_info at %X"),
                              "Itanium RTTI memory scanner",
                              var34.RF
                           );
                           this.LK++;
                        }
                     }
                  }

                  if (var1) {
                     this.oW.q((Long)var23.getKey(), var27, true);
                  }
               } else {
                  UnitUtil.logError(
                     this.Dw,
                     this.Dw.getSymbolicStringAddress((Long)var23.getKey()),
                     true,
                     RF,
                     S.L("%s: cannot parse type_info at %X"),
                     "Itanium RTTI memory scanner",
                     var23.getKey()
                  );
                  this.LK++;
               }
            }

            if (var3) {
               this.za = this.oW.q(this.nf);
               if (this.za == null) {
                  UnitUtil.logError(this.Dw, null, true, RF, S.L("%s: cannot build type hierarchy graph"), "Itanium RTTI memory scanner");
               }
            }

            if (var2) {
               bby var21 = this.Dw.RF();
               IReferenceType var24 = var21.getVoidReference();
               IReferenceManager var28 = this.Dw.getCodeModel().getReferenceManager();
               long var31 = this.lm;

               while (var31 < this.zz) {
                  cnv var36 = this.q(var31);
                  if (var36 != null) {
                     if (var1) {
                        this.Dw.setDataAt(var31, var24, null);
                        IArrayType var37 = var21.createArray(var24, var36.xK.size());
                        if (this.oW.q(var36.RF)) {
                           IUnmangledData var39 = this.Dw.getCodeAnalyzer().getUnmanglerService().unmangle("_Z" + var36.q, true);
                           if (var39 != null) {
                              var36.q = var39.getFull();
                           }

                           String var13 = "vftable_" + var36.q;
                           this.Dw.q(var36.RF, var37, var13, true, true);
                        } else {
                           this.Dw.setDataAt(var36.RF, var37, null);
                        }

                        long var40 = var36.RF;

                        for (long var15 : var36.xK) {
                           var28.recordInternalReference(var40, var15, ReferenceType.PTR_DATA);
                           var40 += this.oW.q() ? 8L : 4L;
                        }
                     }

                     for (long var41 : var36.xK) {
                        CodePointer var42 = this.Dw.getProcessor().createEntryPoint(var41);
                        if (this.Dw.RF(var42.getAddress()) == null) {
                           this.Dw.getCodeAnalyzer().enqueuePointerForAnalysis(var42, 0, 64);
                        }
                     }

                     this.gP.put(var36.RF, var36);
                     var31 = var36.RF + var36.xK.size() * (this.oW.q() ? 8 : 4);
                  } else {
                     var31 += 4L;
                  }
               }
            }

            if (this.Dw.getCodeAnalyzer().needsAnalysis()) {
               this.Dw.getCodeAnalyzer().analyze();
            }

            return !this.gO.isEmpty();
         }
      } else {
         UnitUtil.logTrace(this.Dw, null, RF, S.L("%s: cannot find sections to scan"), "Itanium RTTI memory scanner");
         return false;
      }
   }

   private boolean gO() {
      ISegmentInformation var1 = CodeObjectUnitUtil.findSectionByName(this.Dw.getCodeObjectContainer(), ".rodata");
      if (var1 == null) {
         return false;
      } else {
         ISegmentInformation var2 = CodeObjectUnitUtil.findSectionByName(this.Dw.getCodeObjectContainer(), ".data.rel.ro");
         if (var2 == null) {
            return false;
         } else {
            this.lm = this.Dw.getVirtualImageBase() + Math.min(var1.getOffsetInMemory(), var2.getOffsetInMemory());
            this.zz = this.Dw.getVirtualImageBase()
               + Math.max(var1.getOffsetInMemory() + var1.getSizeInMemory(), var2.getOffsetInMemory() + var2.getSizeInMemory());
            return this.lm < this.zz;
         }
      }
   }

   private boolean nf() {
      ISegmentInformation var1 = CodeObjectUnitUtil.findSectionByName(this.Dw.getCodeObjectContainer(), ".text");
      if (var1 == null) {
         return false;
      } else {
         this.JY = this.Dw.getVirtualImageBase() + var1.getOffsetInMemory();
         this.HF = this.Dw.getVirtualImageBase() + var1.getOffsetInMemory() + var1.getSizeInMemory();
         return this.JY < this.HF;
      }
   }

   private cnv q(long var1) {
      boolean var3 = this.oW.q();

      try {
         long var6 = var3 ? this.Uv.readLong(var1) : this.Uv.readInt(var1) & 4294967295L;
         cnx var8 = (cnx)this.nf.get(var6);
         if (var8 == null) {
            return null;
         } else {
            long var4 = var1 + (var3 ? 8L : 4L);
            long var9 = var4;

            ArrayList var11;
            for (var11 = new ArrayList(); var4 < this.zz; var4 += var3 ? 8L : 4L) {
               long var12 = var3 ? this.Uv.readLong(var4) : this.Uv.readInt(var4) & 4294967295L;
               if (!this.q(var12, false)) {
                  break;
               }

               var11.add(var12);
            }

            if (!var11.isEmpty()) {
               cnv var15 = new cnv();
               var15.q = var8.q();
               var15.RF = var9;
               var15.xK = var11;
               return var15;
            } else {
               return null;
            }
         }
      } catch (MemoryException var14) {
         return null;
      }
   }

   private boolean q(long var1, boolean var3) {
      if (var3) {
         var1 += this.Dw.getVirtualImageBase();
      }

      return var1 >= this.JY && var1 < this.HF;
   }

   @Override
   public cnr q() {
      return this.za;
   }

   @Override
   public String RF() {
      return this.za.q(true, true);
   }

   @Override
   public Map xK() {
      return this.gP;
   }

   @Override
   public String Dw() {
      return Strings.ff("%s: %d type_infos parsed - %d vftables recovered", "Itanium RTTI memory scanner", this.nf.size(), this.gP.size());
   }

   @Override
   public int Uv() {
      return this.LK;
   }

   public Map oW() {
      return this.gO;
   }
}
