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
import com.pnfsoftware.jebglobal.ciy;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Ser
public class vb extends AbstractCodeObjectUnit implements IELFUnit {
   private static final ILogger HF = GlobalLog.getLogger(vb.class);
   @SerId(1)
   tl q;
   @SerId(2)
   List RF = new ArrayList();
   @SerId(3)
   long xK;
   @SerId(4)
   List Dw = new ArrayList();
   @SerId(5)
   long Uv;
   @SerId(6)
   long oW;
   @SerId(7)
   String gO;
   @SerId(8)
   ME nf = new ME();
   @SerId(9)
   vX gP = new vX();
   @SerId(10)
   boolean za;
   @SerId(11)
   private int LK;
   @SerId(12)
   private boolean io;
   @SerId(13)
   private boolean qa;
   @SerId(14)
   private eo Hk;
   @SerId(15)
   private CU Me;
   @SerId(16)
   private List PV = new ArrayList();
   @SerId(18)
   private String oQ;
   @SerId(19)
   private String xW;
   @SerId(21)
   oM lm;
   @SerId(22)
   List zz;
   @SerTransient
   public boolean JY;

   @Override
   public void postDeserialization(IRuntimeProject var1) {
      if (this.za && this.LK == 2 && this.getChildren().isEmpty()) {
         this.LK = 1;
      }
   }

   public vb(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(var2, "elf", var1, var3, var4, var5);
   }

   @Override
   public boolean isELF64() {
      return this.q.q == 2;
   }

   @Override
   public boolean isProcessed() {
      return super.isProcessed() && this.LK == 2;
   }

   @Override
   protected boolean processInternal() {
      if (this.LK == 0) {
         try (SeekableByteChannel var1 = this.getInput().getChannel()) {
            long var35 = var1.size();
            if (var35 <= 64L) {
               return false;
            }

            ByteBuffer var4;
            var4 = ChannelUtil.read(var1, 0L, 64, false);
            this.q = tl.q(var4);
            label488:
            if (this.q.za != 0L) {
               if ((this.q.PV || this.q.HF >= 32) && (!this.q.PV || this.q.HF >= 56)) {
                  var4 = ChannelUtil.read(var1, this.q.za, this.q.LK * this.q.HF, this.q.Me);
                  int var37 = 0;

                  while (true) {
                     if (var37 >= this.q.LK) {
                        break label488;
                     }

                     kY var6 = kY.q(var4, this.q.PV);
                     this.RF.add(var6);
                     var37++;
                  }
               }

               Object[] var85 = new Object[]{this.q.HF};
               return false;
            }

            if (this.RF.isEmpty() && (this.q.oW == 2 || this.q.oW == 3)) {
               this.addNotification(new UnitNotification(NotificationType.WARNING, S.L("ELF file: expected program headers table is not present")));
            }

            eM var38 = null;
            if (this.q.lm != 0L) {
               if (!this.q.PV && this.q.io < 40 || this.q.PV && this.q.io < 64) {
                  Object[] var88 = new Object[]{this.q.HF};
                  return false;
               }

               long var39 = this.q.lm + this.q.qa * this.q.io;
               if (var39 > var1.size()) {
                  this.q.qa = 0;
                  this.addNotification(
                     new UnitNotification(NotificationType.CORRUPTION, S.L("ELF file: too small to accomodate the section headers table; truncating to 0"))
                  );
               } else {
                  var4 = ChannelUtil.read(var1, this.q.lm, this.q.qa * this.q.io, this.q.Me);

                  for (int var8 = 0; var8 < this.q.qa; var8++) {
                     eM var9 = eM.q(var4, this.q.PV);
                     this.Dw.add(var9);
                     if (var9.RF == 6) {
                        if (var38 != null) {
                           this.logWarn(true, S.L("Multiple SHT_DYNAMIC sections"));
                        }

                        var38 = var9;
                     }
                  }
               }
            }

            if (this.q.Hk != 0) {
               if (this.q.Hk >= this.q.qa) {
                  this.q.Hk = 0;
               } else {
                  try {
                     eM var40 = (eM)this.Dw.get(this.q.Hk);
                     byte[] var7 = ChannelUtil.read(var1, var40.Uv, (int)var40.oW, this.q.Me).array();
                     HA var43 = new HA(var7);

                     for (eM var10 : this.Dw) {
                        String var11 = var43.q(var10.q);
                        var10.q(var11);
                     }
                  } catch (Exception var31) {
                  }
               }
            }

            this.Uv = Long.MAX_VALUE;
            long var41 = 0L;
            if (this.q.oW != 1) {
               for (kY var47 : this.RF) {
                  if (var47.q == 1 && var47.Uv != 0L) {
                     if (Longs.compareUnsigned(var47.xK, this.Uv) < 0) {
                        this.Uv = var47.xK;
                     }

                     long var52 = var47.xK + var47.Uv;
                     if (Longs.compareUnsigned(var52, var41) > 0) {
                        var41 = var52;
                     }
                  }
               }

               this.oW = this.Uv != Long.MAX_VALUE && var41 != 0L ? var41 - this.Uv : 0L;
               this.io = this.oW > 0L;
               if (this.Uv == Long.MAX_VALUE) {
                  this.Uv = 0L;
               }
            }

            ej var45 = null;
            if (this.q.oW != 1) {
               for (kY var53 : this.RF) {
                  if (var53.q == 1) {
                     if (var53.Uv != 0L) {
                        Assert.a(Longs.compareUnsigned(var53.xK, this.Uv) >= 0, "Illegal LOAD section");
                        int var58 = this.q(var53.oW);
                        SegmentInformation var12 = new SegmentInformation("LOAD", var53.RF, var53.Dw, var53.xK - this.Uv, var53.Uv, var58);
                        var12.setAlignment(var53.gO);
                        this.addSegment(var12);
                     }
                  } else if (var53.q == 2) {
                     this.xK = var53.xK - this.Uv;
                     if (var38 != null && var53.Dw < var38.oW) {
                        String var59 = Strings.ff(
                           S.L("ELF file: size of PT_DYNAMIC program entry (%Xh) is less then size of SHT_DYNAMIC section entry (%Xh)"), var53.Dw, var38.oW
                        );
                        this.addNotification(new UnitNotification(NotificationType.WARNING, var59));
                     }

                     long var60;
                     if (var53.RF < var1.size() && (var60 = var53.RF + var53.Dw) >= var53.RF && var60 < var1.size()) {
                        var4 = ChannelUtil.read(var1, var53.RF, (int)var53.Dw, this.q.Me, var4);
                        var45 = ej.q(var4, this.q.PV);
                     } else {
                        String var13 = S.L("ELF file: invalid PT_DYNAMIC program entry");
                        this.addNotification(new UnitNotification(NotificationType.ERROR, var13));
                     }
                  }
               }

               for (eM var54 : this.Dw) {
                  int var61 = this.xK(var54.xK);
                  if (!this.Uv(var54.Uv)) {
                     var61 |= 536870912;
                  }

                  long var64 = var54.Dw == 0L ? 0L : var54.Dw - this.Uv;
                  if (var54.RF != 8) {
                     if (Long.compareUnsigned(var54.Uv, var35) > 0) {
                        var61 |= 536870912;
                     } else {
                        long var14 = var54.Uv + var54.oW;
                        if (Long.compareUnsigned(var14, var54.Uv) < 0) {
                           var61 |= 536870912;
                        } else if (Long.compareUnsigned(var14, var35) > 0) {
                           var61 |= 536870912;
                        }
                     }
                  }

                  SegmentInformation var73 = new SegmentInformation(var54.lm, var54.Uv, var54.oW, var64, var54.oW, var61);
                  var73.setAlignment(var54.gP);
                  this.addSection(var73);
               }
            } else {
               long var50 = 0L;
               ArrayList var62 = new ArrayList();

               for (eM var69 : this.Dw) {
                  int var74 = this.xK(var69.xK);
                  SegmentInformation var15 = new SegmentInformation(var69.lm, var69.Uv, var69.getType() == 8 ? 0L : var69.oW, var69.Uv, var69.oW, var74);
                  if (var69.getType() == 8) {
                     var62.add(var15);
                  }

                  var50 = Longs.maxUnsigned(var50, var69.Uv + var69.oW);
                  this.addSection(var15);
               }

               for (SegmentInformation var70 : var62) {
                  var70.setOffsetInMemory(var50);
                  var50 += var70.getSizeInMemory();
               }

               for (ISegmentInformation var71 : this.getSections()) {
                  SegmentInformation var75 = new SegmentInformation((SegmentInformation)var71);
                  var75.setFlags(var75.getFlags() | -2147483648);
                  this.addSegment(var75);
               }

               this.Uv = 0L;
               this.oW = var50;
            }

            LoaderInformation.Builder var51 = new LoaderInformation.Builder();
            int var55 = 0;
            if (this.q.oW == 3) {
               var55 |= 4;
               var55 |= 32;
            } else if (this.q.oW == 1) {
               var55 |= 8;
               var55 |= 32;
            }

            var51.setFlags(var55);
            var51.setVersion("ELF v" + this.q.xK);
            var51.setTargetProcessor(this.q.q());
            var51.setTargetSubsystem(this.q.RF());
            var51.setEndianness(this.q.Me ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN);
            var51.setWordSize(this.q.PV ? 64 : 32);
            var51.setCompilationTimestamp(0L);
            var51.setImageBase(this.Uv);
            var51.setImageSize(this.oW);
            var51.setEntryPoint(this.q.gP - this.Uv);
            var51.setOverlayOffset(0L);
            var51.setNotes(
               Strings.ff(
                  "ELF header: type=0x%X, machine=0x%X(%s), osabi=0x%X, flags=0x%X", this.q.oW, this.q.gO, ELF.getEMString(this.q.gO), this.q.Dw, this.q.zz
               )
            );
            this.setLoaderInformation(var51.build());
            Object[] var86 = new Object[]{this.getLoaderInformation()};

            for (int var63 = 0; var63 < this.Dw.size(); var63++) {
               eM var68 = (eM)this.Dw.get(var63);
               if (var68.RF != 8 && this.Uv(var68.Uv)) {
                  String var72 = Strings.safe2(var68.lm, "section_" + var63);
                  var86 = new Object[]{var63, var72};
                  int var76 = (int)var68.oW;
                  var4 = ChannelUtil.readBestEffort(var1, var68.Uv, var76, this.q.Me, var4);
                  int var77 = var4.limit();
                  if (var77 < var76) {
                     ((SegmentInformation)this.getSection(var63)).setSizeInFile(var77);
                     this.logWarn(
                        true,
                        S.L("Section #%d ('%s') may contain invalid information: %d bytes were expected (offset 0x%X), only %d were read"),
                        var63,
                        var72,
                        var76,
                        var68.Uv,
                        var77
                     );
                  }

                  if (var68.RF == 6) {
                     if (var45 == null) {
                        var45 = ej.q(var4, this.q.PV);
                     }
                  } else if (var68.RF == 2 || var68.RF == 11) {
                     qa var79 = com.pnfsoftware.jeb.corei.parsers.elf.qa.q(var4, this.q.PV);
                     var79.Dw = var68.Uv;
                     var79.RF = var63;
                     var79.xK = var68.RF == 11;
                     this.nf.q(var79, false);
                     if (var68.gO > 0 && var68.gO < this.Dw.size()) {
                        eM var17 = (eM)this.Dw.get(var68.gO);
                        if (var17.RF == 3 && var17.oW > 0L && var17.Uv > 0L) {
                           ByteBuffer var18 = ChannelUtil.read(var1, var17.Uv, (int)var17.oW, this.q.Me);
                           HA var19 = new HA(Arrays.copyOf(var18.array(), var18.limit()));

                           for (LR var21 : var79.q) {
                              var21.q(var19.q(var21.q));
                           }
                        }
                     }
                  } else if (var68.RF == 9 || var68.RF == 4) {
                     try {
                        CI var78 = CI.q(var4, this.q.PV, var68.RF == 4);
                        var78.Uv = var68.Uv;
                        var78.RF = var63;
                        var78.xK = var68.gO;
                        var78.Dw = var68.nf;
                        this.gP.q(var78);
                     } catch (Exception var28) {
                        this.logWarn(true, S.L("The relocation section '%s' was not processed"), var68.lm);
                     }
                  } else if (var68.RF == 7 || var68.lm.equals(".note.android.ident")) {
                     try {
                        Uz var16 = Uz.q(var4, this.q.PV);
                        if (var16 != null) {
                           this.PV.add(var16);
                        }
                     } catch (Exception var27) {
                        this.logWarn(true, S.L("The notes section '%s' was not processed"), var68.lm);
                     }
                  } else if (var68.RF == 1 && Strings.equals(var68.lm, ".comment")) {
                     try {
                        this.gO = Formatter.escapeBytes(Arrays.copyOf(var4.array(), var4.limit())).replaceAll("\\\\u0000", "\n");
                     } catch (Exception var26) {
                        this.logWarn(true, S.L("The comments section '%s' was not processed"), var68.lm);
                     }
                  } else if (var68.RF == 1 && Strings.startsWith(var68.lm, ".ctors")) {
                     try {
                        this.Hk = eo.q(var4, this.q.PV, var68.oW);
                     } catch (Exception var25) {
                        this.logWarn(true, S.L("The .ctor section '%s' was not processed"), var68.lm);
                     }
                  } else if (var68.RF == 1 && Strings.startsWith(var68.lm, ".dtors")) {
                     try {
                        this.Me = CU.q(var4, this.q.PV, var68.oW);
                     } catch (Exception var24) {
                        this.logWarn(true, S.L("The .dtor section '%s' was not processed"), var68.lm);
                     }
                  }

                  for (IELFSectionProcessor var82 : ELFPluginsService.getInstance().getSectionProcessors()) {
                     try {
                        ChainedOperationResult var83 = var82.process(this, var68, var4);
                        if (var83.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
                           if (var83.getResult() != null) {
                              if (((IELFSectionProcessor.Result)var83.getResult()).appendedDescription != null) {
                                 if (this.oQ == null) {
                                    this.oQ = "";
                                 }

                                 this.oQ = this.oQ + "\n" + ((IELFSectionProcessor.Result)var83.getResult()).appendedDescription;
                              }

                              if (((IELFSectionProcessor.Result)var83.getResult()).appendedMetadata != null) {
                                 if (this.xW == null) {
                                    this.xW = "";
                                 }

                                 this.xW = this.xW + "\n" + ((IELFSectionProcessor.Result)var83.getResult()).appendedMetadata;
                              }
                           }

                           if (var83.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
                              break;
                           }
                        }
                     } catch (Exception var30) {
                        HF.catching(var30, S.L("A custom ELF section processor threw an exception"));
                     }
                  }

                  try {
                     IUnit var81 = this.getUnitProcessor()
                        .process(var68.lm, new BytesInput(Arrays.copyOf(var4.array(), var4.limit()), 0, var4.limit(), var68.lm), this, null, true);
                     if (var81 != null && (!(var81 instanceof com.pnfsoftware.jebglobal.qa) || !((com.pnfsoftware.jebglobal.qa)var81).q())) {
                        this.addChild(var81);
                     }
                  } catch (Exception var29) {
                     HF.error("%s", var29.getMessage());
                  }
               }
            }

            if (var45 != null) {
               this.lm = new oM(this, var45, this.q.PV);
               this.lm.q(var1, this.q.Me, this.nf, this.gP);
            }

            this.q(this.nf, this.nf.xK(), this.gP.q());
         } catch (IOException var33) {
            Object[] var84 = new Object[]{var33};
            HF.catching(var33);
            return false;
         } catch (RuntimeException var34) {
            String var2 = Strings.safe(var34.getMessage());
            if (!var2.isEmpty()) {
               Object[] var10000 = new Object[0];
            }

            HF.catching(var34);
            return false;
         }

         if (this.getSegmentCount() > 0) {
            this.za = this.getRawMemoryMappedImage() != null;
         }

         this.LK = 1;
      }

      if (this.q()) {
         this.addNotification(new UnitNotification(NotificationType.INFO, S.L("ELF file likely contains *non* position-independent code")));
      }

      this.oW();
      this.gO();

      try {
         this.zz = ciy.q(this);
      } catch (Exception var23) {
         HF.catching(var23);
      }

      return true;
   }

   private void oW() {
      boolean var1 = false;
      StringBuilder var2 = new StringBuilder();
      var2.append(S.L("ELF metadata\n\n"));
      if (this.gO != null) {
         var1 = true;
         Strings.ff(var2, S.L("- Comment:\n"));
         String[] var3 = Strings.splitLines(this.gO);
         if (var3 != null && var3.length > 0) {
            for (String var7 : var3) {
               if (!Strings.isBlank(var7)) {
                  var2.append("\n  + ");
                  var2.append(var7);
               }
            }
         }
      }

      if (this.PV != null && this.PV.size() != 0) {
         var1 = true;
         var2.append(S.L("\n\n- Notes:\n"));

         for (Uz var10 : this.PV) {
            if (var10.q() != null) {
               for (Nz var12 : var10.q()) {
                  var2.append("\n  + ");
                  var2.append(var12.toString());
               }
            }
         }
      }

      if (this.xW != null && this.xW.length() > 0) {
         var1 = true;
         var2.append(this.xW);
      }

      if (var1) {
         IUnit var9 = this.getUnitProcessor().process("metadata", new BytesInput(Strings.encodeUTF8(var2.toString())), this);
         this.addChild(var9);
      }
   }

   private void gO() {
      if (!this.qa && this.LK == 1) {
         if (this.za) {
            try {
               String var2 = q(this.q);
               IUnit var1;
               if (var2 != null) {
                  var1 = this.getUnitProcessor().process(var2 + " image", this.getInput(), this, var2, true, true);
               } else {
                  var1 = this.getUnitProcessor().process(null, this.getInput(), this, "code_disa", true, true);
               }

               if (var1 != null) {
                  this.addChild(var1);
               } else {
                  this.logError(true, S.L("No matching disassembler found."));
               }
            } catch (Exception var3) {
               HF.catching(var3);
               this.addNotification(new UnitNotification(NotificationType.UNSUPPORTED_FEATURE, S.L("The machine code was not disassembled")));
            }
         }

         this.LK = 2;
      }
   }

   @Override
   public IUnit getImageUnit() {
      for (IUnit var2 : this.getChildren()) {
         if (var2 instanceof INativeCodeUnit || var2.getName().equals("image") || var2.getName().endsWith(" image")) {
            return var2;
         }
      }

      return null;
   }

   private void q(ME var1, Collection var2, Collection var3) {
      long var4 = this.Uv + this.oW;
      IELFSymbolProcessor var6 = ELFPluginsService.getInstance().createSymbolProcessor(this);

      for (qa var8 : var2) {
         int var9 = -1;

         for (LR var11 : var8.q) {
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
            if (var11.Uv == 65521) {
               var47 |= 8;
            }

            if (this.q.getType() == 1) {
               var11.zz = var11.Uv == 0 || var11.Uv == 65522;
            } else if (var11.RF == 0L) {
               var47 |= 1;
               var11.zz = true;
            } else {
               var47 |= 2;
            }

            long var13 = 0L;
            long var15 = var11.xK;
            if (this.q.oW == 1) {
               if (var11.zz) {
                  if (var8.RF != -1) {
                     var13 = this.getSection(var8.RF).getOffsetInMemory() + var9 * (this.q.PV ? 24 : 16);
                     var15 = this.q.PV ? 24L : 16L;
                  }
               } else if (var11.gO == 2 || var11.gO == 1) {
                  var13 = this.getSection(var11.Uv).getOffsetInMemory() + var11.RF;
               } else if (var11.gO == 3) {
                  var13 = this.getSection(var11.Uv).getOffsetInMemory();
               }
            } else if (var11.zz) {
               if (this.q() && var8.RF != -1) {
                  var13 = this.getSection(var8.RF).getOffsetInMemory() + var9 * (this.q.PV ? 24 : 16);
                  var15 = this.q.PV ? 24L : 16L;
               }
            } else if (var11.RF != 0L && var11.RF >= this.Uv && var11.RF <= var4 && (var11.gO == 1 || var11.gO == 2 || var11.gO == 0 || var11.gO == 10)) {
               var13 = var11.RF - this.Uv;
            }

            if (var13 != 0L || var11.gO != 2) {
               SymbolType var17 = this.q(var11, this.q.getType());
               SymbolInformation var18 = new SymbolInformation(var17, var47, 0L, var11.gP, 0L, var13, var15);
               var11.za = var13;
               this.addSymbol(var18);
            }
         }
      }

      if (this.lm != null) {
         int var19 = 0;

         for (long var33 : this.lm.oW) {
            byte var41 = 0;
            SymbolInformation var48 = new SymbolInformation(SymbolType.FUNCTION, var41, 0L, "preinitializer_" + var19, 0L, var33 - this.Uv, 0L);
            this.addSymbol(var48);
            var19++;
         }

         var19 = 0;

         for (long var34 : this.lm.q) {
            byte var42 = 0;
            SymbolInformation var49 = new SymbolInformation(SymbolType.FUNCTION, var42, 0L, "initializer_" + var19, 0L, var34 - this.Uv, 0L);
            this.addSymbol(var49);
            var19++;
         }

         var19 = 0;

         for (long var35 : this.lm.RF) {
            byte var43 = 0;
            SymbolInformation var50 = new SymbolInformation(SymbolType.FUNCTION, var43, 0L, "finalizer_" + var19, 0L, var35 - this.Uv, 0L);
            this.addSymbol(var50);
            var19++;
         }
      }

      if (this.Hk != null) {
         int var22 = 0;

         for (long var36 : this.Hk.q()) {
            byte var44 = 0;
            SymbolInformation var51 = new SymbolInformation(SymbolType.FUNCTION, var44, 0L, "ctor_" + var22, 0L, var36 - this.Uv, 0L);
            this.addSymbol(var51);
            var22++;
         }
      }

      if (this.Me != null) {
         int var23 = 0;

         for (long var37 : this.Me.q()) {
            byte var45 = 0;
            SymbolInformation var52 = new SymbolInformation(SymbolType.FUNCTION, var45, 0L, "dtor_" + var23, 0L, var37 - this.Uv, 0L);
            this.addSymbol(var52);
            var23++;
         }
      }

      if (var6 != null && !var6.canImmediatelyUseSymbol()) {
         for (ISymbolInformation var38 : var6.getSymbols(this.getSymbols())) {
            this.addSymbol(var38);
         }
      }

      if (this.q.oW != 1) {
         for (CI var32 : var3) {
            qa var39 = var1.q(var32.xK);
            if (var39 != null) {
               for (Xa var46 : var32.q) {
                  if (var46.Dw != 0) {
                     if (var46.Dw >= 0 && var46.Dw < var39.getCountOfEntries()) {
                        LR var53 = var39.q(var46.Dw);
                        if (var46.getOffset() != var53.RF) {
                           byte var54 = 0;
                           if (var53.zz) {
                              var54 = 1;
                           } else if ((ELF.isRT_JUMP_SLOT(this.q.q(), var46.Uv) || ELF.isRT_GLOB_DAT(this.q.q(), var46.Uv)) && (this.q.oW != 3 || var53.zz)) {
                              var54 = 1;
                           }

                           String var14 = var53.gP;
                           if (var53.gO == 2) {
                              SymbolInformation var57 = new SymbolInformation(SymbolType.PTRFUNCTION, var54, 0L, var14, 0L, var46.q - this.Uv, 0L);
                              this.addSymbol(var57);
                           } else if (var53.gO == 0) {
                              SymbolType var56 = SymbolType.PTROBJECT;
                              if (ELF.isRT_JUMP_SLOT(this.q.q(), var46.Uv)) {
                                 var56 = SymbolType.PTRFUNCTION;
                              }

                              SymbolInformation var16 = new SymbolInformation(var56, var54, 0L, var14, 0L, var46.q - this.Uv, 0L);
                              this.addSymbol(var16);
                           } else if (var53.gO == 1 || var53.gO == 6) {
                              SymbolInformation var55 = new SymbolInformation(SymbolType.PTROBJECT, var54, 0L, var14, 0L, var46.q - this.Uv, 0L);
                              this.addSymbol(var55);
                           }
                        }
                     } else if (var46.Dw != 0) {
                        Object[] var58 = new Object[]{var46.Dw};
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
      KZ var5 = new KZ(this, var1, var2);
      zJ var6 = var5.q();
      if (var6 == null) {
         this.logInfo(true, S.L("ELF relocations will not be applied"));
         return false;
      } else {
         var6.q(var4);
         var6.RF();
         return var6.xK() == 0;
      }
   }

   public boolean q() {
      return this.lm != null && this.lm.q();
   }

   public long q(long var1) {
      return this.Uv + super.convertFileOffsetToRelativeAddress(var1);
   }

   public long RF(long var1) {
      return super.convertRelativeAddressToFileOffset(var1 - this.Uv);
   }

   SymbolType q(LR var1, int var2) {
      switch (var1.getType()) {
         case 0:
            return (var2 == 1 || this.q()) && var1.zz ? SymbolType.EXTERN_DATA : SymbolType.NOTYPE;
         case 1:
            return SymbolType.OBJECT;
         case 2:
         case 10:
            return (var2 == 1 || this.q()) && var1.zz ? SymbolType.EXTERN_FUNCTION : SymbolType.FUNCTION;
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

   int q(int var1) {
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

   int xK(long var1) {
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

   static String q(tl var0) {
      switch (var0.gO) {
         case 3:
            return "x86";
         case 8:
            return var0.PV ? "mips64" : "mips";
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

   public long RF() {
      return this.xK;
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if (UnitFormatterUtil.getPresentationByIdentifier(var1, 1L) == null) {
         var1.addPresentation(new oL(this, 1L, S.L("ELF Program Table (raw)"), false), false);
         if (this.zz != null && !this.zz.isEmpty()) {
            var1.addPresentation(new Vj(this, 2L, "DWARF", false), false);
         }
      }

      return var1;
   }

   @Override
   public IELFHeader getHeader() {
      return this.q;
   }

   @Override
   public List getProgramEntries() {
      return Collections.unmodifiableList(this.RF);
   }

   @Override
   public IELFProgramEntry getProgramEntry(int var1) {
      return var1 >= 0 && var1 < this.RF.size() ? (IELFProgramEntry)this.RF.get(var1) : null;
   }

   @Override
   public List getSectionEntries() {
      return Collections.unmodifiableList(this.Dw);
   }

   public eM RF(int var1) {
      return var1 >= 0 && var1 < this.Dw.size() ? (eM)this.Dw.get(var1) : null;
   }

   public eM xK(int var1) {
      for (eM var3 : this.Dw) {
         if (var3.getType() == var1) {
            return var3;
         }
      }

      return null;
   }

   @Override
   public String getSectionName(int var1) {
      eM var2 = this.RF(var1);
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
      return Collections.unmodifiableList(this.nf.xK());
   }

   @Override
   public List getRelocationTables() {
      return Collections.unmodifiableList(this.gP.q());
   }

   @Override
   public IELFDynamicTable getDynamicTable() {
      return this.lm;
   }

   @Override
   public List getDwarfDIEs() {
      return this.zz;
   }

   private boolean Dw(long var1) {
      if (this.q.PV) {
         return var1 != -1L;
      } else {
         return (var1 & -4294967296L) != 0L ? false : var1 != 4294967295L;
      }
   }

   private boolean Uv(long var1) {
      return this.Dw(var1);
   }

   public ME xK() {
      return this.nf;
   }

   public vX Dw() {
      return this.gP;
   }

   public String Uv() {
      return this.gO;
   }

   @Override
   public String getDescription() {
      String var1 = super.getDescription();
      StringBuilder var2 = new StringBuilder(var1);

      try {
         var2.append('\n');
         this.q.q(this, var2);
         var2.append('\n');
         Strings.ff(var2, S.L("Program table (%d segments):\n"), this.RF.size());
         int var3 = 0;

         for (kY var5 : this.RF) {
            Strings.ff(var2, "- 0x%02X: ", var3);
            var5.q(this, var2);
            var2.append('\n');
            var3++;
         }

         var2.append('\n');
         Strings.ff(var2, S.L("Section table (%d sections):\n"), this.Dw.size());
         var3 = 0;

         for (eM var11 : this.Dw) {
            Strings.ff(var2, "- 0x%02X: ", var3);
            var11.q(this, var2);
            var2.append('\n');
            var3++;
         }

         var2.append('\n');

         for (qa var12 : this.nf.xK()) {
            var2.append(var12.format(this));
         }

         for (CI var13 : this.gP.q()) {
            var2.append(var13.format(this));
         }
      } catch (Exception var6) {
         var2.setLength(0);
         var2.append(var1);
      }

      if (this.oQ != null) {
         var2.append('\n');
         var2.append(this.oQ);
      }

      return var2.toString();
   }
}
