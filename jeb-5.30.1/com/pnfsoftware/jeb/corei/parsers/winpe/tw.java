package com.pnfsoftware.jeb.corei.parsers.winpe;

import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AbstractAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ILabelManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryRanges;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.NativeAnalyzerException;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.type.IArrayType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.StringEncoding;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.CoffDebugDirectoryEntry;
import com.pnfsoftware.jeb.core.units.codeobject.IPECOFFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.IPEDataDirectory;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ChannelUtil;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.INet;
import com.pnfsoftware.jeb.util.primitives.Bytes;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.Bm;
import com.pnfsoftware.jebglobal.aae;
import com.pnfsoftware.jebglobal.axg;
import com.pnfsoftware.jebglobal.axn;
import com.pnfsoftware.jebglobal.bbv;
import com.pnfsoftware.jebglobal.cpd;
import com.pnfsoftware.jebglobal.cpf;
import com.pnfsoftware.jebglobal.cpg;
import com.pnfsoftware.jebglobal.cpn;
import com.pnfsoftware.jebglobal.cpr;
import com.pnfsoftware.jebglobal.cti;
import com.pnfsoftware.jebglobal.ctm;
import java.io.File;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.Collection;

@Ser
public class tw extends AbstractAnalyzerExtension {
   private static final ILogger q = GlobalLog.getLogger(tw.class);
   @SerId(1)
   private Integer RF;

   @Override
   public ChainedOperationResult preprocessImage(int var1) {
      Assert.a(this.gca.getContainer() instanceof IPECOFFUnit);
      IPECOFFUnit var2 = (IPECOFFUnit)this.gca.getContainer();
      if (var1 == 0) {
         try {
            this.q(var2);
            this.q(var2, true);
            this.RF(var2);
         } catch (Exception var6) {
            JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("PE preprocessing", var6));
         }

         try {
            this.q();
         } catch (Exception var5) {
            JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("PE API trampo find failed", var5));
         }
      }

      if (this.RF == null || this.RF < 0) {
         try {
            this.RF = this.xK(var2);
         } catch (Exception var4) {
            this.RF = null;
            q.catchingSilent(var4);
         }

         if (this.RF != null && this.RF > 0) {
            q.debug("Applied debug symbols: %d", this.RF);
         }
      }

      return ChainedOperationResult.TRUE_CONTINUE;
   }

   private void q(IPECOFFUnit var1, boolean var2) {
      if (var1.getPEOptionalHeader() != null && var1.getPEOptionalHeader().getDataDirectory() != null) {
         ILabelManager var3 = this.gca.getModel().getLabelManager();
         ITypeManager var4 = this.gca.getTypeManager();
         MemoryRanges var5 = ((aae)this.gca).q();
         boolean var6 = var1.getLoaderInformation().getTargetProcessor().is64Bit();
         long var7 = var1.getLoaderInformation().getImageBase();
         IPEDataDirectory[] var9 = var1.getPEOptionalHeader().getDataDirectory();

         for (int var10 = 0; var10 < var9.length; var10++) {
            IPEDataDirectory var11 = var9[var10];
            if (var11.getPosition() != 0L && var11.getSize() != 0L) {
               long var12 = var11.getPosition() + var7;
               long var14 = var12 + var11.getSize();
               switch (var10) {
                  case 0:
                     if (!(var1 instanceof vn) || ((vn)var1).q() == null) {
                        continue;
                     }

                     CU var26 = ((vn)var1).q();
                     String var28 = Strings.truncateWithSuffix(Strings.safe(var26.zz, "-"), 100, "...");
                     INativeType var30 = var4.getType("IMAGE_EXPORT_DIRECTORY");
                     if (var30 != null) {
                        this.gca.defineData(var12, var30);
                        var3.setLabel(var12, "ExportDirectory", true, true, false);
                        this.gca.recordAnalysisComment(var12, Strings.ff("Export Directory for %s", var28));
                     }

                     if (var26.gO >= 0 && var26.gO <= 100000 && var26.nf >= 0 && var26.nf <= 100000) {
                        if (var26.gO != 0) {
                           long var32 = var26.gP + var7;
                           IArrayType var35 = var4.createArray(var4.getExactInteger(4, false), var26.gO);
                           if (var35 != null) {
                              this.gca.defineData(var32, var35);
                              var3.setLabel(var32, "ExportAddressTable", true, true, false);
                              this.gca.recordAnalysisComment(var32, Strings.ff("Export Address Table for %s", var28));
                           }
                        }

                        if (var26.nf != 0) {
                           long var33 = var26.za + var7;
                           IArrayType var36 = var4.createArray(var4.getExactInteger(4, false), var26.nf);
                           if (var36 != null) {
                              this.gca.defineData(var33, var36);
                              var3.setLabel(var33, "ExportNamePointerTable", true, true, false);
                              this.gca.recordAnalysisComment(var33, Strings.ff("Export Name Pointer Table for %s", var28));
                           }
                        }

                        if (var26.nf != 0) {
                           long var34 = var26.lm + var7;
                           IArrayType var37 = var4.createArray(var4.getExactInteger(2, false), var26.nf);
                           if (var37 != null) {
                              this.gca.defineData(var34, var37);
                              var3.setLabel(var34, "ExportOrdinalTable", true, true, false);
                              this.gca.recordAnalysisComment(var34, Strings.ff("Export Ordinal Table for %s", var28));
                           }
                        }
                     } else {
                        this.gca.recordAnalysisComment(var12, Strings.ff("Bad Export Directory!"));
                     }
                     break;
                  case 1:
                     if (!(var1 instanceof vn) || ((vn)var1).RF() == null) {
                        continue;
                     }

                     ej var25 = ((vn)var1).RF();

                     for (oM var29 : var25.q) {
                        String var31 = Strings.truncateWithSuffix(Strings.safe(var29.gO, "-"), 100, "...");
                        INativeType var20 = var4.getType("IMAGE_IMPORT_DESCRIPTOR");
                        if (var20 != null) {
                           this.gca.defineData(var29.nf, var20);
                           var3.setLabel(var29.nf, Strings.ff("ImportDirectory_%s", var31), true, true, false);
                           this.gca.recordAnalysisComment(var29.nf, Strings.ff("Import Directory for %s", var31));
                        }

                        if (var29.za) {
                           long var21 = var29.RF + var7;
                           int var23 = var6 ? 8 : 4;
                           if (var29.gP != 0) {
                              IArrayType var24 = var4.createArray(var4.getExactInteger(var23, false), var29.gP);
                              if (var24 != null) {
                                 this.gca.defineData(var21, var24);
                                 var3.setLabel(var21, Strings.ff("ImportLookupTable_%s", var31), true, true, false);
                                 this.gca.recordAnalysisComment(var21, Strings.ff("Import Lookup Table for %s", var31));
                              }

                              var14 = Math.max(var14, var21 + var29.gP * var23);
                           }

                           var14 = Math.max(var14, var29.lm);
                        }
                     }
                     break;
                  case 6:
                     if (!(var1 instanceof vn) || ((vn)var1).xK() == null || ((vn)var1).xK().isEmpty()) {
                        continue;
                     }

                     int var16 = 0;

                     for (CoffDebugDirectoryEntry var18 : ((vn)var1).xK()) {
                        INativeType var19 = var4.getType("IMAGE_DEBUG_DIRECTORY");
                        if (var19 != null) {
                           this.gca.defineData(var18.getStartAddress(), var19);
                           var3.setLabel(var18.getStartAddress(), Strings.ff("DebugDirectoryEntry%d", var16), true, true, false);
                        }

                        var14 = Math.max(var14, var18.getAddressOfRawData() + var18.getSizeOfData() + var7);
                        var16++;
                     }
               }

               if (var2 && var5 != null) {
                  var5.remove(var12, var14);
               }
            }
         }
      }
   }

   private void q(IPECOFFUnit var1) {
      ILabelManager var2 = this.gca.getModel().getLabelManager();
      IVirtualMemory var3 = this.gca.getMemory();
      long var4 = ((aae)this.gca).Dw().io();
      boolean var6 = var1.getLoaderInformation().getTargetProcessor().is64Bit();
      if (var1 instanceof com.pnfsoftware.jeb.corei.parsers.wincoff.nI) {
         INativeType var7 = this.gca.getTypeManager().getType("IMAGE_FILE_HEADER");
         if (var7 != null) {
            this.gca.defineData(var4, var7);
            var2.setLabel(var4, "FileHeader", true, true, false);
         }

         try {
            int var8 = var3.readShort(var4 + 2L) & '\uffff';
            byte var9 = 20;
            INativeType var10 = this.gca.getTypeManager().getType("IMAGE_SECTION_HEADER");
            if (var10 != null) {
               for (int var11 = 0; var11 < var8; var11++) {
                  this.gca.defineData(var4 + var9, var10);
                  var2.setLabel(var4 + var9, Strings.ff("SectionHeader%02d", var11), true, true, false);
                  var9 += 40;
               }
            }
         } catch (MemoryException var23) {
            return;
         }
      } else {
         INativeType var24 = this.gca.getTypeManager().getType("IMAGE_DOS_HEADER");
         if (var24 != null) {
            this.gca.defineData(var4, var24);
            var2.setLabel(var4, "MZHeader", true, true, false);
         }

         long var25 = var4 + 64L;
         byte[] var26 = new byte[]{14, 31, -70, 14, 0, -76, 9, -51, 33, -72, 1, 76, -51, 33};
         byte[] var27 = new byte[var26.length];

         try {
            var3.read(var25, var27.length, var27, 0);
            if (Bytes.equals(var27, 0, var26, 0, var26.length)) {
               if (this.gca.getProcessor().getType().isIntel() && this.gca.getModel() instanceof Bm) {
                  while (var25 < var4 + 77L) {
                     Bm var28 = (Bm)this.gca.getModel();
                     cti var30 = new cti(16, false, ctm.xK);
                     IInstruction var14 = var30.parseAt(var3, var25);
                     axn var15 = new axn(var25, var14);
                     var28.q((axg)var15);
                     var25 += var14.getSize();
                  }
               } else {
                  INativeType var12 = this.gca.getTypeManager().getInteger(1, false);
                  IArrayType var13 = this.gca.getTypeManager().createArray(var12, var26.length, true);
                  this.gca.defineData(var25, var13);
               }
            }
         } catch (MemoryException | ProcessorException var22) {
         }

         try {
            int var29 = var3.readInt(var4 + 60L);
            int var31 = var29 + 4 + 20;
            int var32 = var3.readShort(var4 + var29 + 4L + 16L) & '\uffff';
            int var33 = var31 + var32;
            int var16 = var3.readShort(var4 + var29 + 4L + 2L) & '\uffff';
            int var17 = 24 + var32;
            INativeType var18 = this.gca.getTypeManager().getType("IMAGE_NT_HEADERS" + (var6 ? "64" : ""));
            if (var18 != null) {
               this.gca.defineData(var4 + var29, var18, var17);
               var2.setLabel(var4 + var29, "PEHeader", true, true, false);
            }

            INativeType var19 = this.gca.getTypeManager().getType("IMAGE_SECTION_HEADER");
            if (var19 != null) {
               for (int var20 = 0; var20 < var16; var20++) {
                  this.gca.defineData(var4 + var33, var19);
                  var2.setLabel(var4 + var33, Strings.ff("SectionHeader%02d", var20), true, true, false);
                  var33 += 40;
               }
            }
         } catch (MemoryException var21) {
            return;
         }
      }

      this.q(var1.getSections(), var4, var6, var1);
   }

   private void RF(IPECOFFUnit var1) {
      IVirtualMemory var2 = this.gca.getMemory();

      long var3;
      boolean var5;
      try {
         var3 = ((aae)this.gca).Dw().io();
         var5 = var1.getLoaderInformation().getTargetProcessor().is64Bit();
      } catch (Exception var26) {
         return;
      }

      try {
         if (var1.getPEOptionalHeader() != null && var1.getPEOptionalHeader().getDataDirectory().length >= 10) {
            IPEDataDirectory var6 = var1.getPEOptionalHeader().getDataDirectory()[10];
            if (var6.getPosition() > 0L && var6.getSize() > 0L) {
               INativeType var7 = this.gca.getTypeManager().getType("DWORD");
               INativeType var8 = this.gca.getTypeManager().getType("IMAGE_LOAD_CONFIG_DIRECTORY" + (var5 ? "64" : "32"));
               if (var8 != null) {
                  long var9 = var3 + var6.getPosition();
                  int var11 = var2.readInt(var9);
                  if (var11 >= 0 && var11 <= 4096) {
                     INativeDataItem var12 = this.gca.defineData(var9, var8, var11);
                     if (var12 != null) {
                        var12.setName("loadConfigDir");
                        IStructureTypeField var13 = TypeUtil.getStructureField(var8, "SEHandlerTable");
                        IStructureTypeField var14 = TypeUtil.getStructureField(var8, "SEHandlerCount");
                        if (var13 != null && var14 != null && var13.getEndOffset() <= var11 && var14.getEndOffset() <= var11) {
                           long var15 = var2.readPointer(var9 + var13.getOffset());

                           for (long var17 = var2.readPointer(var9 + var14.getOffset()); var17 > 0L; var17--) {
                              int var19 = var2.readInt(var15);
                              long var20 = var3 + var19;
                              this.gca.defineData(var15, var7);
                              this.gca.recordAnalysisComment(var15, Strings.ff("Structured Exception Handler @ %Xh", var20));
                              this.gca.enqueuePointerForAnalysis(new CodePointer(var20));
                              var15 += 4L;
                           }
                        }

                        var13 = TypeUtil.getStructureField(var8, "GuardCFFunctionTable");
                        var14 = TypeUtil.getStructureField(var8, "GuardCFFunctionCount");
                        IStructureTypeField var30 = TypeUtil.getStructureField(var8, "GuardFlags");
                        if (var13 != null && var14 != null && var30 != null && var13.getEndOffset() <= var11 && var14.getEndOffset() <= var11) {
                           long var16 = var2.readPointer(var9 + var13.getOffset());
                           long var18 = var2.readPointer(var9 + var14.getOffset());
                           int var31 = var2.readInt(var9 + var30.getOffset());
                           int var21 = 4 + ((var31 & -268435456) >> 28);
                           this.gca.recordAnalysisComment(var16, Strings.ff("__guard_fids_table"));

                           for (int var22 = 0; var22 < var18; var22++) {
                              int var23 = var2.readInt(var16);
                              long var24 = var3 + var23;
                              this.gca.defineData(var16, var7);
                              this.gca.enqueuePointerForAnalysis(new CodePointer(var24));
                              var16 += var21;
                           }
                        }
                     }
                  }
               }
            }
         }
      } catch (Exception var27) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("PE LOAD_CONFIGURATION parsing", var27));
      }
   }

   private int xK(IPECOFFUnit var1) throws IOException {
      if (this.gca.getDebugInformationPolicy().getUsage() != 2) {
         return -1;
      } else if (var1 instanceof vn && ((vn)var1).xK() != null && !((vn)var1).xK().isEmpty()) {
         for (CoffDebugDirectoryEntry var3 : ((vn)var1).xK()) {
            if (var3.getType() == 2) {
               int var4 = var3.getPointerToRawData();

               try (SeekableByteChannel var5 = var1.getInput().getChannel()) {
                  int var6 = ChannelUtil.getInt(var5, var4, ByteOrder.LITTLE_ENDIAN);
                  if (var6 == 1396986706) {
                     cpd var7 = cpd.q(var5);
                     Object[] var10000 = new Object[]{var7};
                     return this.q(var1, var7);
                  }
               }
            }
         }

         return 0;
      } else {
         return -1;
      }
   }

   private int q(IPECOFFUnit var1, cpd var2) throws IOException {
      int var3 = this.gca.getDebugInformationPolicy().getRetrieval();
      if (var3 == 0) {
         q.warn("PDB: %s", var2);
         return -1;
      } else {
         File var4 = null;
         if (var1.getInput() instanceof FileInput) {
            File var5 = ((FileInput)var1.getInput()).getFile();
            File var6 = IO.replaceExtension(var5, ".pdb");
            if (cpf.q(var2, var6)) {
               var4 = var6;
            }
         }

         if (var4 == null) {
            INet var16 = null;
            if (var3 == 2) {
               IEnginesContext var18 = JebCoreService.getDefaultEnginesContext();
               var16 = var18.getNetworkUtility();
            }

            cpf var19 = cpf.q(false);
            var19.q(var16);
            var4 = var19.q(var2);
            if (var4 == null) {
               return var16 == null ? -1 : 0;
            }
         }

         int var22;
         try (cpg var17 = new cpg(new FileInput(var4))) {
            if (!var17.q(var2)) {
               return 0;
            }

            int var20 = 0;

            for (cpr var8 : var17.RF(var2.RF())) {
               if (var8 instanceof cpn var9) {
                  String var10 = var9.Dw();
                  long var11 = this.q(var1, var9.xK(), var9.RF());
                  if (!Strings.isBlank(var10) && var11 != -1L) {
                     boolean var13 = this.gca.getModel().getLabelManager().setLabel(var11, var10, true, true, false);
                     q.debug("Debug Symbol: %Xh: %s: %s", var11, var10, var13 ? "success" : "failure");
                     var20++;
                  }
               }
            }

            var22 = var20;
         }

         return var22;
      }
   }

   private long q(IPECOFFUnit var1, int var2, int var3) {
      if (var2 < 0 || var2 >= var1.getSegmentCount()) {
         return -1L;
      } else if (var3 == -1) {
         return -1L;
      } else {
         ISegmentInformation var4 = var1.getSegment(var2);
         return ((aae)this.gca).Dw().io() + var4.getOffsetInMemory() + (var3 & 4294967295L);
      }
   }

   boolean q() {
      if (!(this.gca.getContainer() instanceof IPECOFFUnit)) {
         return false;
      } else {
         boolean var1 = false;
         if (this.getUnit().getProcessor().getMode() == 64) {
            var1 = true;
         } else if (this.getUnit().getProcessor().getMode() != 32) {
            return false;
         }

         IVirtualMemory var2 = this.gca.getMemory();
         long var3 = this.getUnit().getVirtualImageBase();
         long var5 = var3 + this.getUnit().getImageSize();
         byte var7 = 6;
         byte[] var8 = new byte[]{-1, 37, 0, 0, 0, 0, -1, 37, 0, 0, 0, 0};
         byte[] var9 = new byte[]{-1, -1, 0, 0, 0, 0, -1, -1, 0, 0, 0, 0};
         long var10 = VirtualMemoryUtil.findBytes(var2, true, var3, -1L, var8, var9);
         if (var10 < 0L) {
            var7 = 8;
            byte[] var12 = new byte[]{-1, 37, 0, 0, 0, 0, 0, 0, -1, 37, 0, 0, 0, 0, 0, 0};
            byte[] var13 = new byte[]{-1, -1, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0, 0, 0, 0, 0};
            var10 = VirtualMemoryUtil.findBytes(var2, true, var3, -1L, var12, var13);
            if (var10 < 0L || (var10 & 7L) != 0L) {
               return false;
            }
         }

         ArrayList var19 = new ArrayList();
         short[] var20 = new short[1];

         for (int[] var14 = new int[1];
            VirtualMemoryUtil.readLEShortSafe(var2, var10, var20) && var20[0] == 9727 && VirtualMemoryUtil.readLEIntSafe(var2, var10 + 2L, var14);
            var10 += var7
         ) {
            long var15 = var14[0];
            if (var1) {
               var15 += var10 + 6L;
            }

            if (var15 < var3 || var15 >= var5) {
               break;
            }

            ISegmentInformation var17 = CodeObjectUnitUtil.findSectionByRelativeAddress(this.gca.getContainer(), var15 - var3);
            if (var17 == null || !".iat".equals(var17.getName())) {
               break;
            }

            var19.add(var10);
         }

         if (var19.size() < 2) {
            return false;
         } else {
            for (long var16 : var19) {
               CodePointer var18 = new CodePointer(var16);
               this.gca.enqueuePointerForAnalysis(var18, 1, 64);
            }

            return true;
         }
      }
   }

   private void q(Collection var1, long var2, boolean var4, IPECOFFUnit var5) {
      for (ISegmentInformation var7 : var1) {
         if (var7.getSizeInFile() > 0L) {
            eo.eo var8 = eo.q(var7.getName());
            if (var8 != null) {
               switch (var8) {
                  case q:
                     this.q(var2, var4, var7);
                     break;
                  case RF:
                     this.RF(var2, var4, var7);
               }
            }
         }
      }
   }

   private long q(long var1, ISegmentInformation var3) {
      return var1 + this.q(var3);
   }

   private long q(ISegmentInformation var1) {
      return var1.getOffsetInMemory();
   }

   private INativeType q(INativeType var1, int var2) {
      return (INativeType)(var2 >= 0 && var2 != 1 ? this.gca.getTypeManager().createArray(var1, var2, true) : var1);
   }

   private void q(long var1, boolean var3, ISegmentInformation var4) {
      if (this.q(var4) != 0L) {
         long var5 = this.q(var1, var4);
         long var7 = var5;

         try {
            int var9 = 0;

            while (var9 < var4.getSizeInMemory()) {
               int var10 = this.gca.getMemory().readInt(var7 + var9 + 4L);
               if (var10 == 0) {
                  break;
               }

               INativeType var11 = this.q(var10);
               if (var11 == null) {
                  return;
               }

               this.gca.defineData(var7 + var9, var11);
               var9 += var10;
               if ((var9 & 3) != 0) {
                  var9 += 4 - (var9 & 3);
               }
            }
         } catch (MemoryException var12) {
            q.catchingSilent(var12);
         }
      }
   }

   private INativeType q(int var1) {
      String var2 = "PE_Reloc_" + var1;
      bbv var3 = (bbv)this.gca.getTypeManager().getType(var2);
      if (var3 == null) {
         INativeType var4 = this.gca.getTypeManager().getInteger(4, false);
         INativeType var5 = this.gca.getTypeManager().getInteger(2, false);
         var3 = (bbv)this.gca.getTypeManager().createStructureOrUnion(var2, 4, 0);
         var3.q("VirtualAddress", var4, 0);
         var3.q("SizeOfBlock", var4, -1);
         int var6 = (var1 - 8) / 2;
         if (var6 < 0) {
            return null;
         }

         INativeType var7 = this.q(var5, var6);
         var3.q("Relocations", var7, -1);
      }

      return var3;
   }

   private void RF(long var1, boolean var3, ISegmentInformation var4) {
      try {
         long var5 = this.q(var1, var4);
         this.q(var5, 0, false, var4);
      } catch (MemoryException var7) {
         q.catchingSilent(var7);
      }
   }

   private void q(long var1, int var3, boolean var4, ISegmentInformation var5) throws MemoryException {
      IVirtualMemory var6 = this.gca.getMemory();
      if (var4) {
         if (var3 + 16 <= var5.getSizeInMemory()) {
            INativeType var16 = this.xK();
            if (var16 != null) {
               this.gca.defineData(var1 + var3, var16);
            }
         }
      } else {
         int var7 = var6.readLEShort(var1 + var3 + 12L) & '\uffff';
         int var8 = var6.readLEShort(var1 + var3 + 14L) & '\uffff';
         if (var3 + 16 <= var5.getSizeInMemory()) {
            INativeType var9 = this.RF();
            if (var9 != null) {
               this.gca.defineData(var1 + var3, var9);
               var3 += 16;

               for (int var10 = 0; var10 < var7 + var8; var10++) {
                  if (var3 + 8 > var5.getSizeInMemory()) {
                     return;
                  }

                  int var11 = var6.readLEInt(var1 + var3);
                  int var12 = var6.readLEInt(var1 + var3 + 4L);
                  var9 = this.q(var11 & -2147483648, var12 & -2147483648);
                  if (var9 == null) {
                     return;
                  }

                  this.gca.defineData(var1 + var3, var9);
                  if ((var11 & -2147483648) != 0) {
                     var11 &= Integer.MAX_VALUE;
                     INativeType var13 = this.gca.getTypeManager().getInteger(2, false);
                     this.gca.defineData(var1 + var11, var13);
                     short var14 = var6.readLEShort(var1 + var11);
                     ((aae)this.gca).q(var1 + var11 + 2L, -1L, StringEncoding.UTF16_LE_ZERO, var14, var14 + 1, true);
                  }

                  if ((var12 & -2147483648) != 0) {
                     var12 &= Integer.MAX_VALUE;
                     this.q(var1, var12, false, var5);
                  } else {
                     this.q(var1, var12, true, var5);
                  }

                  var3 += 8;
               }
            }
         }
      }
   }

   private INativeType RF() {
      String var1 = "PE_Rsrc_ResourceTable";
      bbv var2 = (bbv)this.gca.getTypeManager().getType(var1);
      if (var2 == null) {
         INativeType var3 = this.gca.getTypeManager().getInteger(4, false);
         INativeType var4 = this.gca.getTypeManager().getInteger(2, false);
         var2 = (bbv)this.gca.getTypeManager().createStructureOrUnion(var1, 4, 0);
         var2.q("Characteristics", var3, 0);
         var2.q("Time/Date Stamp", var3, -1);
         var2.q("Major Version", var4, -1);
         var2.q("Minor Version", var4, -1);
         var2.q("Number of Name Entries", var4, -1);
         var2.q("Number of ID Entries", var4, -1);
      }

      return var2;
   }

   private INativeType q(int var1, int var2) {
      String var3 = "PE_Rsrc_ResourceEntry_msbs" + (var1 == 0 ? 0 : 1) + (var2 == 0 ? 0 : 1);
      bbv var4 = (bbv)this.gca.getTypeManager().getType(var3);
      if (var4 == null) {
         INativeType var5 = this.gca.getTypeManager().getInteger(4, false);
         var4 = (bbv)this.gca.getTypeManager().createStructureOrUnion(var3, 4, 0);
         var4.q(var1 == 0 ? "Integer ID" : "Name Offset", var5, 0);
         var4.q(var2 == 0 ? "Data Entry Offset" : "Subdirectory Offset", var5, -1);
      }

      return var4;
   }

   private INativeType xK() {
      String var1 = "PE_Rsrc_ResourceData";
      bbv var2 = (bbv)this.gca.getTypeManager().getType(var1);
      if (var2 == null) {
         INativeType var3 = this.gca.getTypeManager().getInteger(4, false);
         var2 = (bbv)this.gca.getTypeManager().createStructureOrUnion(var1, 4, 0);
         var2.q("Data RVA", var3, 0);
         var2.q("Size", var3, -1);
         var2.q("Codepage", var3, -1);
         var2.q("Reserved", var3, -1);
      }

      return var2;
   }

   private void q(long var1, boolean var3, ISegmentInformation var4, IPECOFFUnit var5) {
      long var6 = -1L;
      if (var5.getPEOptionalHeader() != null && var5.getPEOptionalHeader().getDataDirectory().length >= 10) {
         IPEDataDirectory var8 = var5.getPEOptionalHeader().getDataDirectory()[10];
         if (var8.getPosition() > 0L && var8.getSize() > 0L) {
            var6 = var1 + var8.getPosition();
         }
      }

      if (var6 != -1L) {
         INativeContinuousItem var10 = this.gca.getModel().getItemAt(var6);
         Integer var9 = (Integer)var10.getAttribute("GuardFlags", Integer.class);
         var9;
      }
   }
}
