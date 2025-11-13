package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.output.UnitFormatterUtil;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.NotificationType;
import com.pnfsoftware.jeb.core.units.UnitNotification;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.codeobject.AbstractCodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ELF;
import com.pnfsoftware.jeb.core.units.codeobject.ELFPluginsService;
import com.pnfsoftware.jeb.core.units.codeobject.IELFDynamicTable;
import com.pnfsoftware.jeb.core.units.codeobject.IELFHeader;
import com.pnfsoftware.jeb.core.units.codeobject.IELFProgramEntry;
import com.pnfsoftware.jeb.core.units.codeobject.IELFSectionProcessor;
import com.pnfsoftware.jeb.core.units.codeobject.IELFSymbolProcessor;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ILinkInfoProvider;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.core.units.codeobject.ISymbolInformation;
import com.pnfsoftware.jeb.core.units.codeobject.LoaderInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SegmentInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ChannelUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.aB;
import com.pnfsoftware.jebglobal.cdq;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Ser
public class sy extends AbstractCodeObjectUnit implements IELFUnit {
   private static final ILogger NS = GlobalLog.getLogger(sy.class);
   @SerId(1)
   p pC;
   @SerId(2)
   List A = new ArrayList();
   @SerId(3)
   long kS;
   @SerId(4)
   List wS = new ArrayList();
   @SerId(5)
   long UT;
   @SerId(6)
   long E;
   @SerId(7)
   String sY;
   @SerId(8)
   eW ys = new eW();
   @SerId(9)
   gJ ld = new gJ();
   @SerId(10)
   boolean gp;
   @SerId(11)
   private int vP;
   @SerId(12)
   private boolean xC;
   @SerId(13)
   private boolean ED;
   @SerId(14)
   private Av Sc;
   @SerId(15)
   private Sv ah;
   @SerId(16)
   private List eP = new ArrayList();
   @SerId(18)
   private String UO;
   @SerId(19)
   private String Ab;
   @SerId(21)
   bO oT;
   @SerId(22)
   List fI;
   @SerTransient
   public boolean WR;

   @Override
   public void postDeserialization(IRuntimeProject var1) {
      if (this.gp && this.vP == 2 && this.getChildren().isEmpty()) {
         this.vP = 1;
      }
   }

   public sy(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(var2, "elf", var1, var3, var4, var5);
   }

   @Override
   public boolean isELF64() {
      return this.pC.pC == 2;
   }

   @Override
   public boolean isProcessed() {
      return super.isProcessed() && this.vP == 2;
   }

   @Override
   protected boolean processInternal() {
      if (this.vP == 0) {
         try (SeekableByteChannel var1 = this.getInput().getChannel()) {
            long var37 = var1.size();
            if (var37 <= 64L) {
               return false;
            }

            ByteBuffer var4;
            var4 = ChannelUtil.read(var1, 0L, 64, false);
            this.pC = p.pC(var4);
            label523:
            if (this.pC.gp != 0L) {
               if ((this.pC.eP || this.pC.NS >= 32) && (!this.pC.eP || this.pC.NS >= 56)) {
                  var4 = ChannelUtil.read(var1, this.pC.gp, this.pC.vP * this.pC.NS, this.pC.ah);
                  int var39 = 0;

                  while (true) {
                     if (var39 >= this.pC.vP) {
                        break label523;
                     }

                     uX var6 = uX.pC(var4, this.pC.eP);
                     this.A.add(var6);
                     var39++;
                  }
               }

               Object[] var92 = new Object[]{this.pC.NS};
               return false;
            }

            if (this.A.isEmpty() && (this.pC.E == 2 || this.pC.E == 3)) {
               this.addNotification(new UnitNotification(NotificationType.WARNING, S.L("ELF file: expected program headers table is not present")));
            }

            gb var40 = null;
            if (this.pC.oT != 0L) {
               if (!this.pC.eP && this.pC.xC < 40 || this.pC.eP && this.pC.xC < 64) {
                  Object[] var95 = new Object[]{this.pC.NS};
                  return false;
               }

               long var41 = this.pC.oT + this.pC.ED * this.pC.xC;
               if (var41 > var1.size()) {
                  this.pC.ED = 0;
                  this.addNotification(
                     new UnitNotification(NotificationType.CORRUPTION, S.L("ELF file: too small to accomodate the section headers table; truncating to 0"))
                  );
               } else {
                  var4 = ChannelUtil.read(var1, this.pC.oT, this.pC.ED * this.pC.xC, this.pC.ah);

                  for (int var8 = 0; var8 < this.pC.ED; var8++) {
                     gb var9 = gb.pC(var4, this.pC.eP);
                     this.wS.add(var9);
                     if (var9.A == 6) {
                        if (var40 != null) {
                           this.logWarn(true, S.L("Multiple SHT_DYNAMIC sections"));
                        }

                        var40 = var9;
                     }
                  }
               }
            }

            if (this.pC.Sc != 0) {
               if (this.pC.Sc >= this.pC.ED) {
                  this.pC.Sc = 0;
               } else {
                  try {
                     gb var42 = (gb)this.wS.get(this.pC.Sc);
                     byte[] var7 = ChannelUtil.read(var1, var42.UT, (int)var42.E, this.pC.ah).array();
                     Kr var45 = new Kr(var7);

                     for (gb var10 : this.wS) {
                        String var11 = var45.pC(var10.pC);
                        var10.pC(var11);
                     }
                  } catch (Exception var33) {
                  }
               }
            }

            this.UT = Long.MAX_VALUE;
            long var43 = 0L;
            Boolean var46 = null;
            if (this.pC.E != 1) {
               for (uX var50 : this.A) {
                  if (var50.pC == 1 && var50.E != 0L) {
                     if (Longs.compareUnsigned(var50.kS, this.UT) < 0) {
                        this.UT = var50.kS;
                     }

                     long var54 = var50.kS + var50.E;
                     if (Longs.compareUnsigned(var54, var43) > 0) {
                        var43 = var54;
                     }

                     if (var50.kS == 0L) {
                        if (var46 == null) {
                           var46 = true;
                        }
                     } else {
                        var46 = false;
                     }
                  }
               }

               this.E = this.UT != Long.MAX_VALUE && var43 != 0L ? var43 - this.UT : 0L;
               this.xC = this.E > 0L;
               if (this.UT == Long.MAX_VALUE) {
                  this.UT = 0L;
               }
            }

            Ws var49 = null;
            if (this.pC.E != 1) {
               boolean var51 = false;
               if (Boolean.TRUE.equals(var46)) {
                  this.logWarn(true, "All PT_LOAD segments have v_addr set to 0");
                  if (this.pC.sY == 190 && this.pC.E == 2 && Boolean.TRUE.equals(var46)) {
                     this.logWarn(true, "CUDA executable detected; forcing sequential segments");
                     var51 = true;
                  }
               }

               long var55 = this.UT;

               for (uX var14 : this.A) {
                  long var15 = var14.kS;
                  if (var51) {
                     var15 = var55;
                     if (var14.ys > 1L && var55 % var14.ys != 0L) {
                        var15 = (var55 + var14.ys - 1L) / var14.ys * var14.ys;
                     }
                  }

                  if (var14.pC == 1) {
                     if (var14.E != 0L) {
                        Assert.a(Longs.compareUnsigned(var15, this.UT) >= 0, "Illegal LOAD section");
                        int var17 = this.pC(var14.sY);
                        SegmentInformation var18 = new SegmentInformation("LOAD", var14.A, var14.UT, var15 - this.UT, var14.E, var17);
                        var18.setAlignment(var14.ys);
                        this.addSegment(var18);
                     }

                     var55 = var15 + var14.E;
                  } else if (var14.pC == 2) {
                     this.kS = var15 - this.UT;
                     if (var40 != null && var14.UT < var40.E) {
                        String var76 = Strings.ff(
                           S.L("ELF file: size of PT_DYNAMIC program entry (%Xh) is less then size of SHT_DYNAMIC section entry (%Xh)"), var14.UT, var40.E
                        );
                        this.addNotification(new UnitNotification(NotificationType.WARNING, var76));
                     }

                     long var77;
                     if (var14.A < var1.size() && (var77 = var14.A + var14.UT) >= var14.A && var77 < var1.size()) {
                        var4 = ChannelUtil.read(var1, var14.A, (int)var14.UT, this.pC.ah, var4);
                        var49 = Ws.pC(var4, this.pC.eP);
                     } else {
                        String var19 = S.L("ELF file: invalid PT_DYNAMIC program entry");
                        this.addNotification(new UnitNotification(NotificationType.ERROR, var19));
                     }
                  }
               }

               for (gb var65 : this.wS) {
                  int var70 = this.A(var65.kS);
                  if (!this.wS(var65.UT)) {
                     var70 |= 536870912;
                  }

                  long var16 = var65.wS == 0L ? 0L : var65.wS - this.UT;
                  if (var65.A != 8) {
                     if (Long.compareUnsigned(var65.UT, var37) > 0) {
                        var70 |= 536870912;
                     } else {
                        long var84 = var65.UT + var65.E;
                        if (Long.compareUnsigned(var84, var65.UT) < 0) {
                           var70 |= 536870912;
                        } else if (Long.compareUnsigned(var84, var37) > 0) {
                           var70 |= 536870912;
                        }
                     }
                  }

                  SegmentInformation var85 = new SegmentInformation(var65.oT, var65.UT, var65.E, var16, var65.E, var70);
                  var85.setAlignment(var65.ld);
                  this.addSection(var85);
               }
            } else {
               long var52 = 0L;
               ArrayList var12 = new ArrayList();

               for (gb var66 : this.wS) {
                  int var71 = this.A(var66.kS);
                  SegmentInformation var74 = new SegmentInformation(var66.oT, var66.UT, var66.getType() == 8 ? 0L : var66.E, var66.UT, var66.E, var71);
                  if (var66.getType() == 8) {
                     var12.add(var74);
                  }

                  var52 = Longs.maxUnsigned(var52, var66.UT + var66.E);
                  this.addSection(var74);
               }

               for (SegmentInformation var67 : var12) {
                  var67.setOffsetInMemory(var52);
                  var52 += var67.getSizeInMemory();
               }

               for (ISegmentInformation var68 : this.getSections()) {
                  SegmentInformation var72 = new SegmentInformation((SegmentInformation)var68);
                  var72.setFlags(var72.getFlags() | -2147483648);
                  this.addSegment(var72);
               }

               this.UT = 0L;
               this.E = var52;
            }

            LoaderInformation.Builder var53 = new LoaderInformation.Builder();
            int var56 = 0;
            if (this.pC.E == 3) {
               var56 |= 4;
               var56 |= 32;
            } else if (this.pC.E == 1) {
               var56 |= 8;
               var56 |= 32;
            }

            var53.setFlags(var56);
            var53.setVersion("ELF v" + this.pC.kS);
            var53.setTargetProcessor(this.pC.pC());
            var53.setTargetSubsystem(this.pC.A());
            var53.setEndianness(this.pC.ah ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN);
            var53.setWordSize(this.pC.eP ? 64 : 32);
            var53.setCompilationTimestamp(0L);
            var53.setImageBase(this.UT);
            var53.setImageSize(this.E);
            var53.setEntryPoint(this.pC.ld - this.UT);
            var53.setOverlayOffset(0L);
            var53.setNotes(
               Strings.ff(
                  "ELF header: type=0x%X, machine=0x%X(%s), osabi=0x%X, flags=0x%X", this.pC.E, this.pC.sY, ELF.getEMString(this.pC.sY), this.pC.wS, this.pC.fI
               )
            );
            this.setLoaderInformation(var53.build());
            Object[] var93 = new Object[]{this.getLoaderInformation()};

            for (int var59 = 0; var59 < this.wS.size(); var59++) {
               gb var64 = (gb)this.wS.get(var59);
               if (var64.A != 8 && this.wS(var64.UT)) {
                  String var69 = Strings.safe2(var64.oT, "section_" + var59);
                  var93 = new Object[]{var59, var69};
                  int var73 = (int)var64.E;
                  var4 = ChannelUtil.readBestEffort(var1, var64.UT, var73, this.pC.ah, var4);
                  int var75 = var4.limit();
                  if (var75 < var73) {
                     ((SegmentInformation)this.getSection(var59)).setSizeInFile(var75);
                     this.logWarn(
                        true,
                        S.L("Section #%d ('%s') may contain invalid information: %d bytes were expected (offset 0x%X), only %d were read"),
                        var59,
                        var69,
                        var73,
                        var64.UT,
                        var75
                     );
                  }

                  if (var64.A == 6) {
                     if (var49 == null) {
                        var49 = Ws.pC(var4, this.pC.eP);
                     }
                  } else if (var64.A == 2 || var64.A == 11) {
                     m var81 = m.pC(var4, this.pC.eP);
                     var81.wS = var64.UT;
                     var81.A = var59;
                     var81.kS = var64.A == 11;
                     this.ys.pC(var81, false);
                     if (var64.sY > 0 && var64.sY < this.wS.size()) {
                        gb var87 = (gb)this.wS.get(var64.sY);
                        if (var87.A == 3 && var87.E > 0L && var87.UT > 0L) {
                           ByteBuffer var89 = ChannelUtil.read(var1, var87.UT, (int)var87.E, this.pC.ah);
                           Kr var20 = new Kr(Arrays.copyOf(var89.array(), var89.limit()));

                           for (nA var22 : var81.pC) {
                              var22.pC(var20.pC(var22.pC));
                           }
                        }
                     }
                  } else if (var64.A == 9 || var64.A == 4) {
                     try {
                        ma var80 = ma.pC(var4, this.pC.eP, var64.A == 4);
                        var80.UT = var64.UT;
                        var80.A = var59;
                        var80.kS = var64.sY;
                        var80.wS = var64.ys;
                        this.ld.pC(var80);
                     } catch (Exception var30) {
                        this.logWarn(true, S.L("The relocation section '%s' was not processed"), var64.oT);
                     }
                  } else if (var64.A == 1879047936) {
                     try {
                        int var79 = ELF.getRelativeRelocationTypeForMachine(this.pC.sY);
                        ma var86 = ma.pC(var4, this.pC.eP, var79);
                        var86.UT = var64.UT;
                        var86.A = var59;
                        var86.kS = var64.sY;
                        var86.wS = var64.ys;
                        this.ld.pC(var86);
                     } catch (Exception var29) {
                        this.logWarn(true, S.L("The relocation section '%s' was not processed"), var64.oT);
                     }
                  } else if (var64.A == 7 || var64.oT.equals(".note.android.ident")) {
                     try {
                        Mh var78 = Mh.pC(var4, this.pC.eP);
                        if (var78 != null) {
                           this.eP.add(var78);
                        }
                     } catch (Exception var28) {
                        this.logWarn(true, S.L("The notes section '%s' was not processed"), var64.oT);
                     }
                  } else if (var64.A == 1 && Strings.equals(var64.oT, ".comment")) {
                     try {
                        this.sY = Formatter.escapeBytes(Arrays.copyOf(var4.array(), var4.limit())).replaceAll("\\\\u0000", "\n");
                     } catch (Exception var27) {
                        this.logWarn(true, S.L("The comments section '%s' was not processed"), var64.oT);
                     }
                  } else if (var64.A == 1 && Strings.startsWith(var64.oT, ".ctors")) {
                     try {
                        this.Sc = Av.pC(var4, this.pC.eP, var64.E);
                     } catch (Exception var26) {
                        this.logWarn(true, S.L("The .ctor section '%s' was not processed"), var64.oT);
                     }
                  } else if (var64.A == 1 && Strings.startsWith(var64.oT, ".dtors")) {
                     try {
                        this.ah = Sv.pC(var4, this.pC.eP, var64.E);
                     } catch (Exception var25) {
                        this.logWarn(true, S.L("The .dtor section '%s' was not processed"), var64.oT);
                     }
                  }

                  for (IELFSectionProcessor var88 : ELFPluginsService.getInstance().getSectionProcessors()) {
                     try {
                        ChainedOperationResult var90 = var88.process(this, var64, var4);
                        if (var90.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
                           if (var90.getResult() != null) {
                              if (((IELFSectionProcessor.Result)var90.getResult()).appendedDescription != null) {
                                 if (this.UO == null) {
                                    this.UO = "";
                                 }

                                 this.UO = this.UO + "\n" + ((IELFSectionProcessor.Result)var90.getResult()).appendedDescription;
                              }

                              if (((IELFSectionProcessor.Result)var90.getResult()).appendedMetadata != null) {
                                 if (this.Ab == null) {
                                    this.Ab = "";
                                 }

                                 this.Ab = this.Ab + "\n" + ((IELFSectionProcessor.Result)var90.getResult()).appendedMetadata;
                              }
                           }

                           if (var90.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
                              break;
                           }
                        }
                     } catch (Exception var32) {
                        NS.catching(var32, S.L("A custom ELF section processor threw an exception"));
                     }
                  }

                  try {
                     IUnit var83 = this.getUnitProcessor()
                        .process(var64.oT, new BytesInput(Arrays.copyOf(var4.array(), var4.limit()), 0, var4.limit(), var64.oT), this, null, true);
                     if (var83 != null && (!(var83 instanceof aB) || !((aB)var83).pC())) {
                        this.addChild(var83);
                     }
                  } catch (Exception var31) {
                     NS.error("%s", var31.getMessage());
                  }
               }
            }

            if (var49 != null) {
               this.oT = new bO(this, var49, this.pC.eP);
               this.oT.pC(var1, this.pC.ah, this.ys, this.ld);
            }

            this.pC(this.ys, this.ys.pC(), this.ld.pC());
         } catch (IOException var35) {
            Object[] var91 = new Object[]{var35};
            NS.catching(var35);
            return false;
         } catch (RuntimeException var36) {
            String var2 = Strings.safe(var36.getMessage());
            if (!var2.isEmpty()) {
               Object[] var10000 = new Object[0];
            }

            NS.catching(var36);
            return false;
         }

         if (this.getSegmentCount() > 0) {
            this.gp = this.getRawMemoryMappedImage() != null;
         }

         this.vP = 1;
      }

      if (this.pC()) {
         this.addNotification(new UnitNotification(NotificationType.INFO, S.L("ELF file likely contains *non* position-independent code")));
      }

      this.UT();
      this.E();

      try {
         this.fI = cdq.pC(this);
      } catch (Exception var24) {
         NS.catching(var24);
      }

      return true;
   }

   private void UT() {
      boolean var1 = false;
      StringBuilder var2 = new StringBuilder();
      var2.append(S.L("ELF metadata\n\n"));
      if (this.sY != null) {
         var1 = true;
         Strings.ff(var2, S.L("- Comment:\n"));
         String[] var3 = Strings.splitLines(this.sY);
         if (var3 != null && var3.length > 0) {
            for (String var7 : var3) {
               if (!Strings.isBlank(var7)) {
                  var2.append("\n  + ");
                  var2.append(var7);
               }
            }
         }
      }

      if (this.eP != null && this.eP.size() != 0) {
         var1 = true;
         var2.append(S.L("\n\n- Notes:\n"));

         for (Mh var10 : this.eP) {
            if (var10.pC() != null) {
               for (GK var12 : var10.pC()) {
                  var2.append("\n  + ");
                  var2.append(var12.toString());
               }
            }
         }
      }

      if (this.Ab != null && this.Ab.length() > 0) {
         var1 = true;
         var2.append(this.Ab);
      }

      if (var1) {
         IUnit var9 = this.getUnitProcessor().process("metadata", new BytesInput(Strings.encodeUTF8(var2.toString())), this);
         this.addChild(var9);
      }
   }

   private void E() {
      if (!this.ED && this.vP == 1) {
         if (this.gp) {
            try {
               String var2 = pC(this.pC);
               IUnit var1;
               if (var2 != null) {
                  var1 = this.getUnitProcessor().process(var2 + "-image", this.getInput(), this, var2, true, true);
               } else {
                  var1 = this.getUnitProcessor().process(null, this.getInput(), this, "code_disa", true, true);
               }

               if (var1 != null) {
                  this.addChild(var1);
               } else {
                  this.logError(true, S.L("No matching disassembler found."));
               }
            } catch (Exception var3) {
               NS.catching(var3);
               this.addNotification(new UnitNotification(NotificationType.UNSUPPORTED_FEATURE, S.L("The machine code was not disassembled")));
            }
         }

         this.vP = 2;
      }
   }

   @Override
   public IUnit getImageUnit() {
      for (IUnit var2 : this.getChildren()) {
         if (var2 instanceof INativeCodeUnit || var2.getName().equals("image") || var2.getName().endsWith("-image")) {
            return var2;
         }
      }

      return null;
   }

   private void pC(eW var1, Collection var2, Collection var3) {
      long var4 = this.UT + this.E;
      IELFSymbolProcessor var6 = ELFPluginsService.getInstance().createSymbolProcessor(this);

      for (m var8 : var2) {
         int var9 = -1;

         for (nA var11 : var8.pC) {
            var9++;
            if (var6 != null) {
               ISymbolInformation var12 = var6.processSymbol(var11);
               if (var12 != null) {
                  if (var6.canImmediatelyUseSymbol()) {
                     this.addSymbol(var12);
                     Object[] var10000 = new Object[]{var12};
                  }
                  continue;
               }
            }

            byte var47 = 0;
            if (var11.UT == 65521) {
               var47 |= 8;
            }

            if (this.pC.getType() == 1) {
               var11.fI = var11.UT == 0 || var11.UT == 65522;
            } else if (var11.A == 0L) {
               var47 |= 1;
               var11.fI = true;
            } else {
               var47 |= 2;
            }

            long var13 = 0L;
            long var15 = var11.kS;
            if (this.pC.E == 1) {
               if (var11.fI) {
                  if (var8.A != -1) {
                     var13 = this.getSection(var8.A).getOffsetInMemory() + var9 * (this.pC.eP ? 24 : 16);
                     var15 = this.pC.eP ? 24L : 16L;
                  }
               } else if (var11.sY == 2 || var11.sY == 1) {
                  var13 = this.getSection(var11.UT).getOffsetInMemory() + var11.A;
               } else if (var11.sY == 3) {
                  var13 = this.getSection(var11.UT).getOffsetInMemory();
               }
            } else if (var11.fI) {
               if (this.pC() && var8.A != -1) {
                  var13 = this.getSection(var8.A).getOffsetInMemory() + var9 * (this.pC.eP ? 24 : 16);
                  var15 = this.pC.eP ? 24L : 16L;
               }
            } else if (var11.A != 0L && var11.A >= this.UT && var11.A <= var4 && (var11.sY == 1 || var11.sY == 2 || var11.sY == 0 || var11.sY == 10)) {
               var13 = var11.A - this.UT;
            }

            if (var13 != 0L || var11.sY != 2) {
               SymbolType var17 = this.pC(var11, this.pC.getType());
               SymbolInformation var18 = new SymbolInformation(var17, var47, 0L, var11.ld, 0L, var13, var15);
               var11.gp = var13;
               this.addSymbol(var18);
            }
         }
      }

      if (this.oT != null) {
         int var19 = 0;

         for (long var33 : this.oT.E) {
            byte var41 = 0;
            SymbolInformation var48 = new SymbolInformation(SymbolType.FUNCTION, var41, 0L, "preinitializer_" + var19, 0L, var33 - this.UT, 0L);
            this.addSymbol(var48);
            var19++;
         }

         var19 = 0;

         for (long var34 : this.oT.pC) {
            byte var42 = 0;
            SymbolInformation var49 = new SymbolInformation(SymbolType.FUNCTION, var42, 0L, "initializer_" + var19, 0L, var34 - this.UT, 0L);
            this.addSymbol(var49);
            var19++;
         }

         var19 = 0;

         for (long var35 : this.oT.A) {
            byte var43 = 0;
            SymbolInformation var50 = new SymbolInformation(SymbolType.FUNCTION, var43, 0L, "finalizer_" + var19, 0L, var35 - this.UT, 0L);
            this.addSymbol(var50);
            var19++;
         }
      }

      if (this.Sc != null) {
         int var22 = 0;

         for (long var36 : this.Sc.pC()) {
            byte var44 = 0;
            SymbolInformation var51 = new SymbolInformation(SymbolType.FUNCTION, var44, 0L, "ctor_" + var22, 0L, var36 - this.UT, 0L);
            this.addSymbol(var51);
            var22++;
         }
      }

      if (this.ah != null) {
         int var23 = 0;

         for (long var37 : this.ah.pC()) {
            byte var45 = 0;
            SymbolInformation var52 = new SymbolInformation(SymbolType.FUNCTION, var45, 0L, "dtor_" + var23, 0L, var37 - this.UT, 0L);
            this.addSymbol(var52);
            var23++;
         }
      }

      if (var6 != null && !var6.canImmediatelyUseSymbol()) {
         for (ISymbolInformation var38 : var6.getSymbols(this.getSymbols())) {
            this.addSymbol(var38);
         }
      }

      if (this.pC.E != 1) {
         for (ma var32 : var3) {
            m var39 = var1.pC(var32.kS);
            if (var39 != null) {
               for (Tb var46 : var32.pC) {
                  if (var46.wS != 0) {
                     if (var46.wS >= 0 && var46.wS < var39.getCountOfEntries()) {
                        nA var53 = var39.pC(var46.wS);
                        if (var46.getOffset() != var53.A) {
                           byte var54 = 0;
                           if (var53.fI) {
                              var54 = 1;
                           } else if ((ELF.isRT_JUMP_SLOT(this.pC.pC(), var46.UT) || ELF.isRT_GLOB_DAT(this.pC.pC(), var46.UT)) && (this.pC.E != 3 || var53.fI)
                              )
                            {
                              var54 = 1;
                           }

                           String var14 = var53.ld;
                           if (var53.sY == 2) {
                              SymbolInformation var57 = new SymbolInformation(SymbolType.PTRFUNCTION, var54, 0L, var14, 0L, var46.pC - this.UT, 0L);
                              this.addSymbol(var57);
                           } else if (var53.sY == 0) {
                              SymbolType var56 = SymbolType.PTROBJECT;
                              if (ELF.isRT_JUMP_SLOT(this.pC.pC(), var46.UT)) {
                                 var56 = SymbolType.PTRFUNCTION;
                              }

                              SymbolInformation var16 = new SymbolInformation(var56, var54, 0L, var14, 0L, var46.pC - this.UT, 0L);
                              this.addSymbol(var16);
                           } else if (var53.sY == 1 || var53.sY == 6) {
                              SymbolInformation var55 = new SymbolInformation(SymbolType.PTROBJECT, var54, 0L, var14, 0L, var46.pC - this.UT, 0L);
                              this.addSymbol(var55);
                           }
                        }
                     } else if (var46.wS != 0) {
                        Object[] var58 = new Object[]{var46.wS};
                     }
                  }
               }
            }
         }
      }
   }

   @Override
   protected boolean shouldAllocateFullImage() {
      return false;
   }

   @Override
   protected boolean applyRelocations(IVirtualMemory var1, long var2, ILinkInfoProvider var4) {
      io var5 = new io(this, var1, var2);
      Pj var6 = var5.pC();
      if (var6 == null) {
         this.logInfo(true, S.L("ELF relocations will not be applied"));
         return false;
      } else {
         var6.pC(var4);
         var6.pC();
         return var6.A() == 0;
      }
   }

   public boolean pC() {
      return this.oT != null && this.oT.pC();
   }

   public long pC(long var1) {
      return super.convertRelativeAddressToFileOffset(var1 - this.UT);
   }

   SymbolType pC(nA var1, int var2) {
      switch (var1.getType()) {
         case 0:
            return (var2 == 1 || this.pC()) && var1.fI ? SymbolType.EXTERN_DATA : SymbolType.NOTYPE;
         case 1:
            return SymbolType.OBJECT;
         case 2:
         case 10:
            return (var2 == 1 || this.pC()) && var1.fI ? SymbolType.EXTERN_FUNCTION : SymbolType.FUNCTION;
         case 3:
            return SymbolType.SECTION;
         case 4:
            return SymbolType.FILE;
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         default:
            return SymbolType.UNKNOWN;
      }
   }

   int pC(int var1) {
      byte var2 = 0;
      if ((var1 & 1) != 0) {
         var2 |= 4;
      }

      if ((var1 & 2) != 0) {
         var2 |= 1;
      }

      if ((var1 & 4) != 0) {
         var2 |= 2;
      }

      return var2;
   }

   int A(long var1) {
      byte var3 = 0;
      if ((var1 & 4L) != 0L) {
         var3 |= 4;
      }

      if ((var1 & 1L) != 0L) {
         var3 |= 1;
      }

      if ((var1 & 2L) != 0L) {
         var3 |= 2;
      }

      return var3;
   }

   static String pC(p var0) {
      switch (var0.sY) {
         case 3:
            return "x86";
         case 8:
            return var0.eP ? "mips64" : "mips";
         case 40:
            return "arm";
         case 62:
            return "x86_64";
         case 83:
         case 185:
            return "avr";
         case 183:
            return "arm64";
         default:
            return null;
      }
   }

   public long A() {
      return this.kS;
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if (UnitFormatterUtil.getPresentationByIdentifier(var1, 1L) == null) {
         var1.addPresentation(new HE(this, 1L, S.L("ELF Program Table (raw)"), false), false);
         if (this.fI != null && !this.fI.isEmpty()) {
            var1.addPresentation(new qt(this, 2L, "DWARF", false), false);
         }
      }

      return var1;
   }

   @Override
   public IELFHeader getHeader() {
      return this.pC;
   }

   @Override
   public List getProgramEntries() {
      return Collections.unmodifiableList(this.A);
   }

   @Override
   public IELFProgramEntry getProgramEntry(int var1) {
      return var1 >= 0 && var1 < this.A.size() ? (IELFProgramEntry)this.A.get(var1) : null;
   }

   @Override
   public List getSectionEntries() {
      return Collections.unmodifiableList(this.wS);
   }

   public gb A(int var1) {
      return var1 >= 0 && var1 < this.wS.size() ? (gb)this.wS.get(var1) : null;
   }

   @Override
   public String getSectionName(int var1) {
      gb var2 = this.A(var1);
      if (var2 != null) {
         String var3 = var2.getName();
         if (var3 != null) {
            return var3;
         }
      }

      return ELF.getSHNString(var1);
   }

   @Override
   public List getSymbolTables() {
      return Collections.unmodifiableList(this.ys.pC());
   }

   @Override
   public List getRelocationTables() {
      return Collections.unmodifiableList(this.ld.pC());
   }

   @Override
   public IELFDynamicTable getDynamicTable() {
      return this.oT;
   }

   @Override
   public List getDwarfDIEs() {
      return this.fI;
   }

   private boolean kS(long var1) {
      if (this.pC.eP) {
         return var1 != -1L;
      } else {
         return (var1 & -4294967296L) != 0L ? false : var1 != 4294967295L;
      }
   }

   private boolean wS(long var1) {
      return this.kS(var1);
   }

   public eW kS() {
      return this.ys;
   }

   public gJ wS() {
      return this.ld;
   }

   @Override
   public String getDescription() {
      String var1 = super.getDescription();
      StringBuilder var2 = new StringBuilder(var1);

      try {
         var2.append('\n');
         this.pC.pC(this, var2);
         var2.append('\n');
         Strings.ff(var2, S.L("Program table (%d segments):\n"), this.A.size());
         int var3 = 0;

         for (uX var5 : this.A) {
            Strings.ff(var2, "- 0x%02X: ", var3);
            var5.pC(this, var2);
            var2.append('\n');
            var3++;
         }

         var2.append('\n');
         Strings.ff(var2, S.L("Section table (%d sections):\n"), this.wS.size());
         var3 = 0;

         for (gb var11 : this.wS) {
            Strings.ff(var2, "- 0x%02X: ", var3);
            var11.pC(this, var2);
            var2.append('\n');
            var3++;
         }

         var2.append('\n');

         for (m var12 : this.ys.pC()) {
            var2.append(var12.format(this));
         }

         for (ma var13 : this.ld.pC()) {
            var2.append(var13.format(this));
         }
      } catch (Exception var6) {
         var2.setLength(0);
         var2.append(var1);
      }

      if (this.UO != null) {
         var2.append('\n');
         var2.append(this.UO);
      }

      return var2.toString();
   }
}
