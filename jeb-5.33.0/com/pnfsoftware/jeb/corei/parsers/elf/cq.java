package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.NativeCommentManager;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.LinuxSyscallResolver;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AbstractAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ILabelManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReference;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.NativeAnalyzerException;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ELF;
import com.pnfsoftware.jeb.core.units.codeobject.IELFHeader;
import com.pnfsoftware.jeb.core.units.codeobject.IELFSectionEntry;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.core.units.codeobject.ISymbolInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.Tw;
import com.pnfsoftware.jebglobal.Vr;
import com.pnfsoftware.jebglobal.a;
import com.pnfsoftware.jebglobal.auu;
import com.pnfsoftware.jebglobal.auz;
import com.pnfsoftware.jebglobal.ava;
import com.pnfsoftware.jebglobal.ayq;
import com.pnfsoftware.jebglobal.ayt;
import com.pnfsoftware.jebglobal.ayv;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.stream.Collectors;

@Ser
public class cq extends AbstractAnalyzerExtension {
   private static final ILogger kS = GlobalLog.getLogger(cq.class);
   @SerId(1)
   Map pC = new HashMap();
   @SerTransient
   LinuxSyscallResolver A;
   private static List wS = new ArrayList();

   @SerCustomInitPostGraph
   private void pC() {
      if (this.pC == null) {
         this.pC = new HashMap();
      }
   }

   private LinuxSyscallResolver A() {
      if (this.A == null) {
         this.A = LinuxSyscallResolver.getInstance(this.gca.getProcessor().getType());
      }

      return this.A;
   }

   @Override
   public ChainedOperationResult preprocessImage(int var1) {
      if (var1 == 0) {
         Assert.a(this.gca.getContainer() instanceof IELFUnit);
         IELFUnit var2 = (IELFUnit)this.gca.getContainer();

         try {
            this.pC(var2);
            Vr var3 = ((a)this.gca).wS();
            long var4 = this.getUnit().getVirtualImageBase();

            for (ISymbolInformation var7 : var2.getSymbols()) {
               if ((
                     var7.getType() == SymbolType.PTRFUNCTION && (var7.getFlags() & 1) != 0
                        || ((SymbolInformation)var7).isInternal() && var3.kS()
                        || var7.getType() == SymbolType.EXTERN_FUNCTION
                        || var7.getType() == SymbolType.EXTERN_DATA
                  )
                  && wS.contains(var7.getName())) {
                  INativeContinuousItem var8 = this.gca.getModel().getItemAt(var4 + var7.getSymbolRelativeAddress());
                  if (var8 instanceof auz) {
                     auu var9 = (auu)((auz)var8).UO();
                     if (var9 != null) {
                        var9.wS(true);
                     }
                  }
               }
            }
         } catch (Exception var10) {
            JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("ELF preprocessing", var10));
         }
      }

      return ChainedOperationResult.TRUE_CONTINUE;
   }

   private INativeType pC(String var1) {
      return this.gca.getTypeManager().getType(var1);
   }

   private long pC(long var1, IStructureType var3, int var4) {
      return var1 + var3.getField(var4).getOffset();
   }

   private INativeType pC(INativeType var1, int var2) {
      return (INativeType)(var2 >= 0 && var2 != 1 ? this.gca.getTypeManager().createArray(var1, var2, true) : var1);
   }

   private void pC(long var1, INativeType var3, int var4) {
      if (var4 > 100000) {
         long var5 = var1;
         int var7 = var4;

         while (var7 > 0) {
            int var8 = Math.min(var7, 100000);
            if (this.gca.getModel().isEmptyRange(var5, var8 * var3.getSize())) {
               INativeType var9 = var8 == 1 ? var3 : this.pC(var3, var8);
               this.gca.defineData(var5, var9);
            } else {
               this.A(var5, var3, var8);
            }

            if (var7 <= 100000) {
               break;
            }

            var7 -= 100000;
            var5 += var3.getSize() * 100000;
         }
      } else if (this.gca.getModel().isEmptyRange(var1, var4 * var3.getSize())) {
         INativeType var10 = var4 == 1 ? var3 : this.pC(var3, var4);
         this.gca.defineData(var1, var10);
      } else {
         this.A(var1, var3, var4);
      }
   }

   private void A(long var1, INativeType var3, int var4) {
      for (int var5 = 0; var5 < var4 && var5 < 100000; var5++) {
         long var6 = var1 + var5 * var3.getSize();
         if (this.gca.getModel().isEmptyRange(var6, var3.getSize())) {
            this.gca.defineData(var6, var3);
         }
      }
   }

   private void pC(IELFUnit var1) {
      ILabelManager var2 = this.gca.getModel().getLabelManager();
      NativeCommentManager var3 = this.getUnit().getCommentManager();
      long var4 = ((a)this.gca).wS().NS();
      long var6 = var1.getLoaderInformation().getImageBase();
      boolean var8 = var1.getLoaderInformation().getTargetProcessor().is64Bit();
      INativeType var9 = this.pC(var8 ? "Elf64_Ehdr" : "Elf32_Ehdr");
      if (var9 != null) {
         this.gca.defineData(var4, var9);
         var2.setLabel(var4, "ELFHeader", true, true, false);
         if (TypeUtil.getNonAlias(var9) instanceof IStructureType var10) {
            IELFHeader var11 = var1.getHeader();
            String var12 = var3.memoryToAddress(this.pC(var4, var10, 1));
            var3.addMetaComment(var12, ELF.getETString(var11.getType()), false);
            var12 = var3.memoryToAddress(this.pC(var4, var10, 2));
            var3.addMetaComment(var12, ELF.getEMString(var11.getMachine()), false);
         }
      }

      if (var1.getHeader().getProgramHeaderTableEntryNumber() > 0) {
         long var18 = var4 + var1.getHeader().getProgramHeaderTableOffset();
         INativeType var20 = this.pC(var8 ? "Elf64_Phdr" : "Elf32_Phdr");
         if (var20 != null) {
            for (int var13 = 0; var13 < var1.getHeader().getProgramHeaderTableEntryNumber(); var13++) {
               long var14 = var18 + var13 * var20.getSize();
               var2.setLabel(var18 + var13 * var20.getSize(), "ProgramHeader" + var13, true, true, var13 == 0);
               this.gca.defineData(var14, var20);
               String var16 = var3.memoryToAddress(var14);
               var3.addMetaComment(var16, ELF.getPTString(var1.getProgramEntry(var13).getType()), false);
            }
         }
      }

      this.pC(var1.getSectionEntries(), var4, var6, var8, true);
   }

   private void pC(List var1, long var2, long var4, boolean var6, boolean var7) {
      ArrayList var8 = new ArrayList();

      for (IELFSectionEntry var10 : var1) {
         if (var10.getSize() > 0L && var10.getAddress() >= 0L) {
            ELF.WellKnownSection var11 = ELF.getSection(var10.getType(), var10.getName());
            if (var11 == null) {
               this.pC(var2, var4, var6, var11, var10);
            } else {
               switch (var11) {
                  case dynamic:
                     this.pC(var2, var4, var6, var10);
                     break;
                  case gnuVersion:
                     this.pC(var2, var4, var6 ? "Elf64_Half" : "Elf32_Half", var10);
                     break;
                  case gnuVersion_d:
                     if (!this.pC.isEmpty()) {
                        this.pC(var2, var4, var6 ? "Elf64_Verdef" : "Elf32_Verdef", var6 ? "Elf64_Verdaux" : "Elf32_Verdaux", 1879048189, var10);
                     } else if (var7) {
                        var8.add(var10);
                     }
                     break;
                  case gnuVersion_r:
                     if (!this.pC.isEmpty()) {
                        this.pC(var2, var4, var6 ? "Elf64_Verneed" : "Elf32_Verneed", var6 ? "Elf64_Vernaux" : "Elf32_Vernaux", 1879048191, var10);
                     } else if (var7) {
                        var8.add(var10);
                     }
                     break;
                  case hash:
                     this.wS(var2, var4, var6, var10);
                     break;
                  case debugFrame:
                  case ehFrame:
                     this.UT(var2, var4, var6, var10);
                     break;
                  case ehFrameHdr:
                     this.E(var2, var4, var6, var10);
                     break;
                  case gccExceptTable:
                     this.pC(var2, var4, var10);
                  case debugInfo:
                     break;
                  default:
                     this.pC(var2, var4, var6, var11, var10);
               }
            }
         }
      }

      if (!var8.isEmpty()) {
         this.pC(var8, var2, var4, var6, false);
      }
   }

   private void pC(long var1, long var3, boolean var5, ELF.WellKnownSection var6, IELFSectionEntry var7) {
      if (var7.getType() == 7 || var7.getName().equals(".note.android.ident")) {
         this.A(var1, var3, var5, var6, var7);
      } else if (var7.getType() != 8) {
         if (var7.getType() == 2 || var7.getType() == 11) {
            this.kS(var1, var3, var5, var7);
         } else if (var7.getType() == 9) {
            this.A(var1, var3, var5 ? "Elf64_Rel" : "Elf32_Rel", var7);
         } else if (var7.getType() == 4) {
            this.A(var1, var3, var5 ? "Elf64_Rela" : "Elf32_Rela", var7);
         } else if (var7.getType() == 6) {
            this.pC(var1, var3, var5, var7);
         } else if (var7.getType() == 1879048182) {
            this.A(var1, var3, var5, var7);
         } else if (this.gca.getProcessor().getType().isMIPS()) {
            if (var7.getType() == 1879048234) {
               this.sY(var1, var3, var5, var7);
            } else if (var7.getType() == 1879048198) {
               this.ys(var1, var3, var5, var7);
            }
         } else if (this.gca.getProcessor().getType().isARM()) {
            if (var7.getType() == 1879048193) {
               this.ld(var1, var3, var5, var7);
            } else if (var7.getType() == 1 && (".ARM.extab".equals(var7.getName()) || var7.getName().startsWith(".ARM.extab."))) {
               this.gp(var1, var3, var5, var7);
            }
         }
      }
   }

   private void A(long var1, long var3, boolean var5, ELF.WellKnownSection var6, IELFSectionEntry var7) {
      INativeType var8 = this.pC(var5 ? "Elf64_Nhdr" : "Elf32_Nhdr");
      if (var8 != null && this.pC(var7) != 0L) {
         long var9 = this.A(var1, var3, var7);
         this.gca.defineData(var9, var8);

         try {
            int var11 = this.gca.getMemory().readInt(var1 + var7.getOffset());
            var11 = Math.min(var11, (int)(var7.getSize() - 12L));
            int var12 = this.gca.getMemory().readInt(var1 + var7.getOffset() + 4L);
            var12 = Math.min(var12, (int)(var7.getSize() - 12L - var11));
            if (var11 > 0) {
               this.pC(var9 + 12L, var11);
            }

            if (var12 > 0) {
               int var13 = var11 == 0 ? 0 : (var11 % 4 == 0 ? var11 : var11 + (4 - var11 % 4));
               long var14 = var9 + 12L + var13;
               if (var6 == ELF.WellKnownSection.noteABITag) {
                  INativeType var16 = this.gca.getTypeManager().getInteger(4, false);
                  this.pC(var14, var16, var12 / 4);
               } else {
                  INativeType var20 = this.gca.getTypeManager().getInteger(1, false);
                  this.pC(var14, var20, var12);
               }
            }
         } catch (MemoryException var17) {
            kS.catchingSilent(var17);
         }
      }
   }

   private void pC(long var1, long var3, boolean var5, IELFSectionEntry var6) {
      INativeType var7 = this.pC(var5 ? "Elf64_Dyn" : "Elf32_Dyn");
      if (var7 != null && this.pC(var6) != 0L) {
         INativeType var8 = TypeUtil.getNonAlias(var7);
         ILabelManager var9 = this.gca.getModel().getLabelManager();
         long var10 = this.A(var1, var3, var6);
         int var12 = (int)(var6.getSize() / var7.getSize());

         for (int var13 = 0; var13 < var12; var13++) {
            try {
               long var14 = var10 + var13 * var7.getSize();
               long var16 = this.A(var1, var3, var6) + var13 * var7.getSize();
               long var18 = var5 ? this.gca.getMemory().readLong(var16) : this.gca.getMemory().readInt(var16);
               var9.setLabel(var10 + var13 * var7.getSize(), ELF.getDT((int)var18), true, true, var13 == 0);
               this.gca.defineData(var14, var7);
               if (var18 != 0L && var8 instanceof IStructureType) {
                  long var20 = this.pC(var16, (IStructureType)var8, 1);
                  long var22 = var5 ? this.gca.getMemory().readLong(var20) : this.gca.getMemory().readInt(var20);
                  this.pC.put((int)var18, (int)var22);
               }
            } catch (MemoryException var24) {
               kS.catchingSilent(var24);
            }
         }
      }
   }

   private void pC(long var1, long var3, String var5, IELFSectionEntry var6) {
      this.pC(var1, var3, var5, null, 0, var6);
   }

   private void pC(long var1, long var3, String var5, String var6, int var7, IELFSectionEntry var8) {
      INativeType var9 = this.pC(var5);
      if (var9 != null && this.pC(var8) != 0L) {
         long var10 = this.A(var1, var3, var8);
         int var12;
         if (var7 == 0) {
            var12 = (int)(var8.getSize() / var9.getSize());
         } else {
            Integer var13 = (Integer)this.pC.get(var7);
            if (var13 == null) {
               return;
            }

            var12 = var13;
         }

         this.pC(var10, var9, var12);
         if (var6 != null) {
            INativeType var17 = this.pC(var6);
            if (var17 != null) {
               long var14 = var10 + var12 * var9.getSize();
               int var16 = (int)((var8.getSize() - var12 * var9.getSize()) / var17.getSize());
               this.pC(var14, var17, var16);
            }
         }
      }
   }

   private void A(long var1, long var3, boolean var5, IELFSectionEntry var6) {
      INativeType var7 = this.pC("Elf32_Word");
      INativeType var8 = this.pC("Elf64_Xword");
      if (var7 != null && var8 != null && this.pC(var6) != 0L) {
         long var9 = this.A(var1, var3, var6);

         try {
            int var11 = this.gca.getMemory().readInt(var9);
            int var12 = this.gca.getMemory().readInt(var9 + 4L);
            int var13 = this.gca.getMemory().readInt(var9 + 8L);
            INativeType var14 = this.pC(var5, var11, var13, var12, var7, var8, var6.getSize());
            if (var14 == null) {
               return;
            }

            this.gca.defineData(var9, var14);
         } catch (MemoryException var15) {
            kS.catchingSilent(var15);
         }
      }
   }

   private INativeType pC(boolean var1, int var2, int var3, int var4, INativeType var5, INativeType var6, long var7) {
      String var9 = "Elf" + (var1 ? "64_" : "32_") + var2 + "_" + var3 + "__GnuHash";
      ayv var10 = (ayv)this.gca.getTypeManager().getType(var9);
      if (var10 == null) {
         ayv var11 = (ayv)this.gca.getTypeManager().getType("Elf_GnuHashHeader");
         if (var11 == null) {
            var11 = (ayv)this.gca.getTypeManager().createStructureOrUnion("Elf_GnuHashHeader", 4, 0);
            var11.pC("nbucket", var5, 0);
            var11.pC("symndx", var5, -1);
            var11.pC("maskwords", var5, -1);
            var11.pC("shift2", var5, -1);
         }

         var10 = (ayv)this.gca.getTypeManager().createStructureOrUnion(var9, 4, 0);
         var10.pC("header", var11, 0);
         var10.pC("bloom_filter", this.pC(var1 ? var6 : var5, var3), -1);
         var10.pC("hash_buckets", this.pC(var5, var2), -1);
         if (var10.getSize() > var7) {
            return null;
         }

         int var12 = (int)(var4 + (var7 - var10.getSize()) / 4L);
         var10.pC("hash_values", this.pC(var5, var12 - var4), -1);
      }

      return var10;
   }

   private void kS(long var1, long var3, boolean var5, IELFSectionEntry var6) {
      INativeType var7 = this.pC(var5 ? "Elf64_Sym" : "Elf32_Sym");
      if (var7 != null && this.pC(var6) != 0L) {
         long var8 = this.A(var1, var3, var6);
         int var10 = (int)(var6.getSize() / var7.getSize());
         this.pC(var8, var7, var10);
      }
   }

   private void A(long var1, long var3, String var5, IELFSectionEntry var6) {
      INativeType var7 = this.pC(var5);
      if (var7 != null && this.pC(var6) != 0L) {
         long var8 = this.A(var1, var3, var6);
         int var10 = (int)(var6.getSize() / var7.getSize());
         this.pC(var8, var7, var10);
      }
   }

   private void wS(long var1, long var3, boolean var5, IELFSectionEntry var6) {
      INativeType var7 = this.pC("Elf32_Word");
      if (var7 != null && this.pC(var6) != 0L) {
         ILabelManager var8 = this.gca.getModel().getLabelManager();

         try {
            long var9 = this.A(var1, var3, var6);
            int var11 = this.gca.getMemory().readInt(var9);
            var11 = Math.min(var11, (int)(var6.getSize() - 8L));
            long var12 = var9 + var7.getSize();
            int var14 = this.gca.getMemory().readInt(var12);
            var14 = Math.min(var14, (int)(var6.getSize() - 8L - var11));
            this.gca.defineData(var9, var7);
            var8.setLabel(var9, "nbucket", true, true, false);
            this.gca.defineData(var12, var7);
            var8.setLabel(var12, "nchain", true, true, false);
            if (var11 > 0) {
               long var15 = var9 + 2 * var7.getSize();
               if (var8.getLabel(var15, 0L, null) == null) {
                  var8.setLabel(var15, "bucket", true, true, false);
               }

               this.pC(var15, var7, var11);
            }

            if (var14 > 0) {
               long var20 = var9 + (2 + var11) * var7.getSize();
               if (var8.getLabel(var20, 0L, null) == null) {
                  var8.setLabel(var20, "chain", true, true, false);
               }

               this.pC(var20, var7, var14);
            }
         } catch (MemoryException var17) {
            kS.catchingSilent(var17);
         }
      }
   }

   private void UT(long var1, long var3, boolean var5, IELFSectionEntry var6) {
      INativeType var7 = this.pC("Elf32_Word");
      INativeType var8 = this.gca.getTypeManager().getInteger(1, false);
      if (var7 != null && var8 != null && this.pC(var6) != 0L) {
         long var9 = this.A(var1, var3, var6);

         try {
            ILabelManager var11 = this.gca.getModel().getLabelManager();
            long var12 = 0L;
            int var14 = -1;
            int var15 = 0;

            while (var12 < var6.getSize()) {
               byte var16 = 4;
               int var17 = this.gca.getMemory().readInt(var9 + var12);
               long var18 = 0L;
               if (var17 == 0 || var12 + var17 > var6.getSize()) {
                  break;
               }

               if (var17 == -1) {
                  var18 = this.gca.getMemory().readLong(var9 + var12 + var16);
                  var16 += 8;
                  if (var12 + var18 > var6.getSize()) {
                     break;
                  }
               }

               long var20 = this.gca.getMemory().readInt(var9 + var12 + var16);
               if (var20 == 0L) {
                  var15 = 0;
                  var11.setLabel(var9 + var12, "CFI_" + ++var14, true, true, false);
               } else {
                  var11.setLabel(var9 + var12, "FDE_" + var14 + "_" + var15, true, true, false);
                  var15++;
               }

               INativeType var22 = this.pC(var5, var17, var18, var8, var7);
               this.gca.defineData(var9 + var12, var22);
               var12 += var16 + (var18 == 0L ? var17 : var18);
            }
         } catch (MemoryException var23) {
            kS.catchingSilent(var23);
         }
      }
   }

   private INativeType pC(boolean var1, int var2, long var3, INativeType var5, INativeType var6) {
      String var7 = (var1 ? "Elf64" : "Elf32") + (var3 == 0L ? "" : "L") + var2 + "_Cfi";
      ayv var8 = (ayv)this.gca.getTypeManager().getType(var7);
      if (var8 == null) {
         var8 = (ayv)this.gca.getTypeManager().createStructureOrUnion(var7, 4, 0);
         var8.pC("length", var6, 0);
         var8.pC("cieId", var6, -1);
         INativeType var9 = this.pC(var5, var2 - 4);
         var8.pC("data", var9, -1);
      }

      return var8;
   }

   private void E(long var1, long var3, boolean var5, IELFSectionEntry var6) {
      long var7 = this.A(var1, var3, var6);

      try {
         int var9 = this.gca.getMemory().readInt(var7 + 8L);
         var9 = Math.min((int)(var6.getSize() - 12L), var9);
         if (var9 < 0) {
            return;
         }

         INativeType var10 = this.pC(var5, var9);
         this.gca.defineData(var7, var10);
      } catch (MemoryException var11) {
         kS.catchingSilent(var11);
      }
   }

   private INativeType pC(boolean var1, int var2) {
      String var3 = (var1 ? "Elf64_" : "Elf32_") + var2 + "_CfiHdr";
      ayv var4 = (ayv)this.gca.getTypeManager().getType(var3);
      if (var4 == null) {
         INativeType var5 = this.gca.getTypeManager().getInteger(1, false);
         INativeType var6 = this.pC("Elf32_Word");
         var4 = (ayv)this.gca.getTypeManager().createStructureOrUnion(var3, 4, 0);
         var4.pC("version", var5, 0);
         var4.pC("eh_frame_ptr_enc", var5, -1);
         var4.pC("fde_count_enc", var5, -1);
         var4.pC("table_enc", var5, -1);
         var4.pC("eh_frame_ptr", var6, -1);
         var4.pC("fde_count", var6, -1);
         String var7 = (var1 ? "Elf64" : "Elf32") + "_CfiHdrBinSearch";
         ayv var8 = (ayv)this.gca.getTypeManager().getType(var7);
         if (var8 == null) {
            var8 = (ayv)this.gca.getTypeManager().createStructureOrUnion(var7, 4, 0);
            var8.pC("initialLocation", var6, 0);
            var8.pC("address", var6, -1);
         }

         INativeType var9 = this.pC(var8, var2);
         var4.pC("binSearchTable", var9, -1);
      }

      return var4;
   }

   private void sY(long var1, long var3, boolean var5, IELFSectionEntry var6) {
      if (this.pC(var6) != 0L) {
         long var7 = this.A(var1, var3, var6);
         INativeType var9 = this.pC(var6.getSize());
         if (var9 == null) {
            return;
         }

         this.gca.defineData(var7, var9);
      }
   }

   private INativeType pC(long var1) {
      INativeType var3 = this.gca.getTypeManager().getInteger(1, false);
      INativeType var4 = this.pC("Elf32_Half");
      INativeType var5 = this.pC("Elf32_Word");
      String var6 = "Elf_Mips_ABIFlags";
      ayv var7 = (ayv)this.gca.getTypeManager().getType(var6);
      if (var7 == null) {
         var7 = (ayv)this.gca.getTypeManager().createStructureOrUnion(var6, 4, 0);
         var7.pC("version", var4, 0);
         var7.pC("isa_level", var3, -1);
         var7.pC("isa_rev", var3, -1);
         var7.pC("gpr_size", var3, -1);
         var7.pC("cpr1_size", var3, -1);
         var7.pC("cpr2_size", var3, -1);
         var7.pC("fp_abi", var3, -1);
         var7.pC("isa_ext", var5, -1);
         var7.pC("ases", var5, -1);
         var7.pC("flags1", var5, -1);
         var7.pC("flags2", var5, -1);
         if (var7.getSize() > var1) {
            return null;
         }
      }

      return var7;
   }

   private void ys(long var1, long var3, boolean var5, IELFSectionEntry var6) {
      if (this.pC(var6) != 0L) {
         long var7 = this.A(var1, var3, var6);
         INativeType var9 = this.pC(var5, var6.getSize());
         if (var9 == null) {
            return;
         }

         this.gca.defineData(var7, var9);
      }
   }

   private INativeType pC(boolean var1, long var2) {
      INativeType var4 = this.pC("Elf32_Word");
      String var5 = (var1 ? "Elf64" : "Elf32") + "_Mips_RegInfo";
      ayv var6 = (ayv)this.gca.getTypeManager().getType(var5);
      if (var6 == null) {
         var6 = (ayv)this.gca.getTypeManager().createStructureOrUnion(var5, 4, 0);
         var6.pC("ri_gprmask", var4, 0);
         if (var1) {
            var6.pC("ri_pad", var4, -1);
         }

         var6.pC("ri_cprmask", this.pC(var4, 4), -1);
         var6.pC("ri_gp_value", var4, -1);
         if (var6.getSize() > var2) {
            return null;
         }
      }

      return var6;
   }

   private void ld(long var1, long var3, boolean var5, IELFSectionEntry var6) {
      if (this.pC(var6) != 0L) {
         long var7 = this.A(var1, var3, var6);
         INativeType var9 = this.A(var5, var6.getSize());
         if (var9 == null) {
            return;
         }

         int var10 = (int)(var6.getSize() / var9.getSize());
         this.pC(var7, var9, var10);
      }
   }

   private INativeType A(boolean var1, long var2) {
      INativeType var4 = this.pC("Elf32_Word");
      String var5 = "Elf_Arm_Unwind";
      ayv var6 = (ayv)this.gca.getTypeManager().getType(var5);
      if (var6 == null) {
         var6 = (ayv)this.gca.getTypeManager().createStructureOrUnion(var5, 4, 0);
         var6.pC("addr", var4, 0);
         var6.pC("unwind", var4, -1);
         if (var6.getSize() > var2) {
            return null;
         }
      }

      return var6;
   }

   private void gp(long var1, long var3, boolean var5, IELFSectionEntry var6) {
      this.pC(var1, var3, var6);
   }

   private void pC(long var1, long var3, IELFSectionEntry var5) {
      if (this.pC(var5) != 0L) {
         INativeType var6 = this.gca.getTypeManager().getInteger(1, false);
         long var7 = this.A(var1, var3, var5);
         this.pC(var7, var6, (int)var5.getSize());
      }
   }

   private void pC(long var1, int var3) {
      this.getUnit().setStringAt(var1, var1 + var3, null, var3 - 1, var3);
   }

   private long A(long var1, long var3, IELFSectionEntry var5) {
      return var3 == 0L ? var1 + this.pC(var5) : this.pC(var5);
   }

   private long pC(IELFSectionEntry var1) {
      IELFUnit var2 = (IELFUnit)this.gca.getContainer();
      return var2.getHeader().getType() != 1 ? var1.getAddress() : var1.getOffset();
   }

   @Override
   public ChainedOperationResult postprocessImage(int var1) {
      if (var1 == 0) {
         Assert.a(this.gca.getContainer() instanceof IELFUnit);
         IELFUnit var2 = (IELFUnit)this.gca.getContainer();
         List var3 = var2.getDwarfDIEs();
         if (var3 != null && this.gca.getDebugInformationPolicy().getUsage() != 0) {
            try {
               rQ var4 = new rQ(var2, this.getUnit(), this.gca);
               var4.pC(var3);
            } catch (Exception var9) {
               JebCoreService.notifySilentExceptionToClient(new yt("dwarf processing errors", var9), var2);
            }
         }

         ISegmentInformation var10 = CodeObjectUnitUtil.findSectionByName(var2, ".plt");
         ISegmentInformation var5 = CodeObjectUnitUtil.findSectionByName(var2, ".got");
         ISegmentInformation var6 = CodeObjectUnitUtil.findSectionByName(var2, ".got.plt");

         try {
            if (((sy)var2).WR) {
               ((sy)var2).WR = false;
               if (var10 != null) {
                  if (var5 != null) {
                     this.pC(var2, var5, var10, true);
                  }

                  if (var6 != null) {
                     this.pC(var2, var6, var10, true);
                  }

                  return ChainedOperationResult.TRUE_CONTINUE;
               }
            }

            if (this.gca.getProcessor().getType().isIntel()) {
               if (var5 != null) {
                  this.pC(var2, var5);
               }

               if (var6 != null) {
                  this.pC(var2, var6);
               }
            }
         } catch (Exception var8) {
            JebCoreService.notifySilentExceptionToClient(new yt("got post-processing", var8), var2);
         }

         if (this.A(var2)) {
            JebCoreService.notifySilentExceptionToClient(new yt("plt without symbols"), var2);
         }
      }

      return ChainedOperationResult.FALSE_CONTINUE;
   }

   private boolean A(IELFUnit var1) {
      long var2 = ((a)this.gca).wS().NS();

      for (ISegmentInformation var5 : var1.getSections()) {
         if (var5.getName().startsWith(".plt")) {
            boolean var6 = true;
            long var7 = var5.getOffsetInMemory() + var2;
            long var9 = var7 + var5.getSizeInMemory();

            for (Long var13 : (Collection)this.gca
               .getModel()
               .getRoutineAddresses()
               .stream()
               .filter(var4 -> var4 >= var7 && var4 <= var9)
               .collect(Collectors.toList())) {
               auu var14 = ((Tw)this.gca.getModel()).E(var13);
               if (var14 != null && var14.getName().startsWith("→")) {
                  var6 = false;
                  break;
               }
            }

            if (var6) {
               return true;
            }
         }
      }

      return false;
   }

   private boolean pC(IELFUnit var1, ISegmentInformation var2) {
      long var3 = ((a)this.gca).wS().NS();
      long var5 = var2.getOffsetInMemory() + var3;
      long var7 = var5 + var2.getSizeInMemory();
      int var9 = this.gca.getProcessor().getDefaultMode();
      SortedMap var10 = this.gca.getModel().getItemsInRange(var5, false, var7, false, var0 -> var0 instanceof auz var1x && var1x.UO() != null);

      for (INativeContinuousItem var12 : var10.values()) {
         long var13 = var12.getMemoryAddress();
         long var15 = 0L;

         try {
            if (var9 == 32) {
               var15 = this.gca.getMemory().readInt(var13) & 4294967295L;
            } else if (var9 == 64) {
               var15 = this.gca.getMemory().readLong(var13);
            }
         } catch (MemoryException var19) {
            continue;
         }

         if (this.gca.getModel().isRoutineHeader(var15)) {
            auu var17 = ((Tw)this.gca.getModel()).E(var15);
            ISegmentInformation var18 = CodeObjectUnitUtil.findSectionByRelativeAddress(var1, var15 - var3);
            if (var17 != null && var17 != ((auz)var12).UO() && var18 != null && var18.getName().startsWith(".plt")) {
               if (var17.UT() == null && ((auz)var12).UO().getPrototype() != null) {
                  var17.setPrototype(((auz)var12).UO().getPrototype());
               }

               ((a)this.gca).pC(var17.E(), (auz)var12, true);
            }
         }
      }

      return true;
   }

   private boolean pC(IELFUnit var1, ISegmentInformation var2, ISegmentInformation var3, boolean var4) {
      if (var4) {
         int var5 = this.gca.getProcessor().getDefaultMode();
         if (var5 != 32 && var5 != 64) {
            return false;
         } else {
            long var6 = ((a)this.gca).wS().NS();
            long var8 = var3.getOffsetInMemory() + var6;
            long var10 = var8 + var3.getSizeInMemory();
            long var12 = var2.getOffsetInMemory() + var6;
            long var14 = var12 + var2.getSizeInMemory();
            SortedMap var16 = this.gca
               .getModel()
               .getItemsInRange(
                  var12,
                  false,
                  var14,
                  false,
                  var1x -> var1x instanceof ava var2x && var2x.getMemorySize() * 8L == var5 && (var2x.UT() instanceof ayq || var2x.UT() instanceof ayt)
               );
            Iterator var17 = var16.values().iterator();

            while (true) {
               ava var19;
               long var20;
               long var22;
               while (true) {
                  if (!var17.hasNext()) {
                     return true;
                  }

                  INativeContinuousItem var18 = (INativeContinuousItem)var17.next();
                  var19 = (ava)var18;
                  var20 = var19.getMemoryAddress();
                  var22 = 0L;

                  try {
                     if (var5 == 32) {
                        var22 = this.gca.getMemory().readInt(var20) & 4294967295L;
                     } else if (var5 == 64) {
                        var22 = this.gca.getMemory().readLong(var20);
                     }
                     break;
                  } catch (MemoryException var31) {
                  }
               }

               if (this.gca.getModel().isRoutineHeader(var22)) {
                  auu var24 = ((Tw)this.gca.getModel()).E(var22);
                  if (var24 != null) {
                     var19.xC();
                     auz var25 = ((a)this.gca).pC(var20, "ptr_" + var24.getName(), var24, var24.getName());
                     if (!((a)this.gca).ys().oT().pC(var24.getName())) {
                        for (IReference var27 : this.gca.getModel().getReferenceManager().getReferencesTo(var20)) {
                           if (var27.getFrom().isInternalAddress()) {
                              long var28 = var27.getFrom().getInternalAddress();
                              if (var28 >= var8 && var28 < var10 && this.gca.getModel().isRoutineHeader(var28)) {
                                 auu var30 = ((Tw)this.gca.getModel()).E(var28);
                                 if (var30.E().getCFG().getInstructionCount() == 1) {
                                    ((a)this.gca).pC(var30.E(), var25, false);
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      } else {
         return true;
      }
   }

   @Override
   public ChainedOperationResult getPreferredBreakingFlow(long var1, IInstruction var3) {
      if (this.A() == null) {
         return ChainedOperationResult.continue_();
      } else if (this.A().isSyscall(var3)) {
         INativeContinuousItem var4 = this.gca.getModel().getItemOver(var1 - 1L);
         if (!(var4 instanceof INativeInstructionItem)) {
            return ChainedOperationResult.continue_();
         } else {
            long var5 = this.A().getSyscallRegisterId(var3);
            Integer var7 = this.pC(((INativeInstructionItem)var4).getInstruction(), var5);
            if (var7 == null) {
               var4 = this.gca.getModel().getItemOver(var4.getMemoryAddress() - 1L);
               if (!(var4 instanceof INativeInstructionItem)) {
                  return ChainedOperationResult.continue_();
               }

               var7 = this.pC(((INativeInstructionItem)var4).getInstruction(), var5);
            }

            if (var7 == null) {
               return new ChainedOperationResult(FlowInformation.NONE, ChainedOperationResult.ContinuationStatus.STOP);
            } else {
               IPrototypeItem var8 = this.A().getPrototype(var7, this.gca.getTypeManager());
               if (Strings.isContainedIn(this.A().getName(var7), "exit", "exit_group")) {
                  return new ChainedOperationResult(new FlowInformation(), ChainedOperationResult.ContinuationStatus.STOP);
               } else {
                  return var8 != null && var8.isNoReturn()
                     ? new ChainedOperationResult(new FlowInformation(), ChainedOperationResult.ContinuationStatus.STOP)
                     : new ChainedOperationResult(FlowInformation.NONE, ChainedOperationResult.ContinuationStatus.STOP);
               }
            }
         }
      } else {
         return ChainedOperationResult.continue_();
      }
   }

   private Integer pC(IInstruction var1, long var2) {
      return var1.getMnemonic().equalsIgnoreCase("mov")
            && ((IInstructionOperandGeneric)var1.getOperand(0)).isRegister()
            && ((IInstructionOperandGeneric)var1.getOperand(1)).isImmediate()
            && ((IInstructionOperandGeneric)var1.getOperand(0)).getOperandValue() == var2
         ? (int)((IInstructionOperandGeneric)var1.getOperand(1)).getOperandValue()
         : null;
   }

   static {
      wS.add("__stack_chk_fail");
      wS.add("__cxa_throw");
      wS.add("__cxa_rethrow");
      wS.add("__cxa_bad_cast");
      wS.add("__cxa_bad_typeid");
      wS.add("__cxa_throw_bad_array_new_length");
      wS.add("__cxa_pure_virtual");
      wS.add("__cxa_deleted_virtual");
      wS.add("__cxa_call_unexpected");
      wS.add("_ZSt9terminatev");
      wS.add("_ZSt16__throw_bad_castv");
   }
}
