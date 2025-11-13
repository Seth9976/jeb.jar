package com.pnfsoftware.jeb.corei.parsers.winpe;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.input.SubInput;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.NotificationType;
import com.pnfsoftware.jeb.core.units.UnitNotification;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.codeobject.AbstractCodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.CoffDebugDirectoryEntry;
import com.pnfsoftware.jeb.core.units.codeobject.ICOFFHeader;
import com.pnfsoftware.jeb.core.units.codeobject.ICOFFSectionHeader;
import com.pnfsoftware.jeb.core.units.codeobject.ILinkInfoProvider;
import com.pnfsoftware.jeb.core.units.codeobject.IPECOFFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.IPEOptionalHeader;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.core.units.codeobject.LoaderInformation;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SegmentInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolType;
import com.pnfsoftware.jeb.core.units.impl.ContainerUnit;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ByteBufferUtil;
import com.pnfsoftware.jeb.util.io.ChannelUtil;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.cpv;
import com.pnfsoftware.jebglobal.cpw;
import com.pnfsoftware.jebglobal.cpx;
import com.pnfsoftware.jebglobal.cpz;
import com.pnfsoftware.jebglobal.cqe;
import com.pnfsoftware.jebglobal.cqf;
import com.pnfsoftware.jebglobal.cqg;
import com.pnfsoftware.jebglobal.cqh;
import com.pnfsoftware.jebglobal.cqi;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Ser
public class vn extends AbstractCodeObjectUnit implements IPECOFFUnit {
   private static final ILogger lm = GlobalLog.getLogger(vn.class);
   @SerId(1)
   nI q;
   @SerId(2)
   Nt RF;
   @SerId(3)
   iZ[] xK;
   @SerId(4)
   boolean Dw;
   @SerId(5)
   boolean Uv;
   @SerId(6)
   boolean oW;
   @SerId(7)
   boolean gO;
   @SerId(8)
   CU nf;
   @SerId(9)
   ej gP;
   @SerId(10)
   List za;

   public vn(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(var2, "winpe", var1, var3, var4, var5);
   }

   @Override
   public boolean isPE64() {
      return this.Dw;
   }

   @Override
   protected boolean processInternal() {
      long var1 = 0L;

      try {
         label604: {
            boolean var7;
            try (SeekableByteChannel var3 = this.getInput().getChannel()) {
               long var4 = var3.size();
               if (var4 <= 64L) {
                  return false;
               }

               if (ChannelUtil.getLEShort(var3, 0L) != 23117) {
                  return false;
               }

               int var6 = ChannelUtil.getLEInt(var3, 60L);
               if (var6 >= 0 && var6 + 4 <= var4) {
                  if (ChannelUtil.getLEInt(var3, var6) != 17744) {
                     return false;
                  }

                  ByteBuffer var66 = ByteBuffer.allocate(20).order(ByteOrder.LITTLE_ENDIAN);
                  var3.position(var6 + 4);
                  if (var3.read(var66) != 20) {
                     return false;
                  }

                  var66.rewind();
                  this.q = nI.q(var66);
                  short var8 = ChannelUtil.getLEShort(var3, var6 + 4 + 20);
                  if (var8 == 523) {
                     this.Dw = true;
                  } else if (var8 != 267) {
                     lm.error(S.L("Invalid optional header magic: %X"), var8);
                     return false;
                  }

                  ByteBuffer var67 = ByteBuffer.allocate(512).order(ByteOrder.LITTLE_ENDIAN);
                  var3.position(var6 + 4 + 20);
                  int var9 = var3.read(var67);
                  if (var9 < 69) {
                     return false;
                  }

                  var67.rewind();
                  var67.limit(var9);
                  this.RF = Nt.q(var67, this.Dw);
                  int var10 = this.RF.zz;
                  int var11 = this.RF.lm;
                  if (MathUtil.isPowerOfTwo(var10) && MathUtil.isPowerOfTwo(var11)) {
                     if (var11 <= 4096) {
                        this.Uv = true;
                     }

                     if (var10 > var11) {
                        lm.error(S.L("Invalid alignments (file=%Xh, section=%Xh)"), var10, var11);
                        return false;
                     }

                     this.xK = new iZ[this.q.xK];
                     if (this.xK.length > 0) {
                        var3.position(var6 + 4 + 20 + this.q.gO);
                        ByteBuffer var68 = ByteBuffer.allocate(40).order(ByteOrder.LITTLE_ENDIAN);
                        long var87 = -1L;

                        for (int var14 = 0; var14 < this.xK.length; var14++) {
                           var68.rewind();
                           if (var3.read(var68) != 40) {
                              return false;
                           }

                           var68.rewind();
                           iZ var15 = iZ.q(var68);
                           this.xK[var14] = var15;
                           long var16 = var15.oW;
                           long var18 = var15.Uv;
                           long var20 = var15.xK;
                           long var22;
                           long var109;
                           if (!this.Uv) {
                              var22 = var16 & -512L;
                              var109 = (var16 + var18 + var10 - 1L & ~(var10 - 1)) - var22;
                              var109 = Math.min(var109, var18 + 4095L & -4096L);
                              if (var20 != 0L) {
                                 var109 = Math.min(var109, var20 + 4095L & -4096L);
                              }
                           } else {
                              var22 = var16 & ~(var10 - 1);
                              var109 = var18;
                              if (var20 != 0L) {
                                 var109 = Math.min(var18, var20 + var11 - 1L & ~(var11 - 1));
                              }
                           }

                           if (var109 > 2147483647L) {
                              throw new RuntimeException("Section is too large");
                           }

                           long var26 = var22 + var109;
                           if (var26 > var1) {
                              var1 = var26;
                           }

                           long var28 = var15.Dw;
                           if (var28 % var11 != 0L) {
                              lm.error(S.L("Invalid segment memory address"));
                              return false;
                           }

                           if (var20 == 0L) {
                              var20 = var109;
                           }

                           if (var87 != -1L && var87 != var28) {
                              String var30 = Strings.ff(S.L("Illegal section, PE unlikely to load: expected RVA= %Xh, actual=%Xh"), var87, var28);
                              this.addNotification(new UnitNotification(NotificationType.CORRUPTION, var30));
                           }

                           long var110 = (var20 + var11 - 1L) / var11 * var11;
                           var87 = var28 + var110;

                           String var32;
                           try {
                              int var33 = 0;

                              while (var33 < var15.RF.length && var15.RF[var33] != 0) {
                                 var33++;
                              }

                              var32 = Strings.decodeASCII(var15.RF, 0, var33);
                           } catch (Exception var49) {
                              var32 = "";
                           }

                           int var112 = this.RF(var15.lm);
                           this.addSegment(new SegmentInformation(var32, var22, var109, var28, var110, var112));
                           Object[] var113 = new Object[]{var32, var22, var109, var28, var110};
                        }
                     }

                     int var88 = (int)var3.size();
                     if (this.xK.length >= 1) {
                        int var13 = (int)this.xK[0].oW;

                        for (int var96 = 1; var13 == 0 && var96 < this.xK.length; var96++) {
                           var13 = (int)this.xK[var96].oW;
                        }

                        if (var13 != 0 && var13 < var88) {
                           var88 = var13;
                        }
                     }

                     int var93 = (int)this.RF.oQ;
                     if (var93 < var88) {
                        var88 = var93;
                     }

                     this.insertSegment(0, new SegmentInformation("<hdr>", 0L, var88, 0L, var88, 2));
                     if (this.q.Uv != 0 && this.q.oW >= 0) {
                        this.q(var3);
                     }
                     break label604;
                  }

                  lm.error(S.L("Invalid alignments, must be a power of 2 (file=%Xh, section=%Xh)"), var10, var11);
                  return false;
               }

               var7 = false;
            }

            return var7;
         }
      } catch (IOException var51) {
         Object[] var10000 = new Object[]{var51};
         return false;
      }

      LoaderInformation.Builder var52 = new LoaderInformation.Builder();
      int var53 = 0;
      if ((this.q.nf & 8192) != 0) {
         var53 |= 4;
         var53 |= 32;
      }

      var52.setFlags(var53);
      var52.setTargetProcessor(this.xK(this.q.RF));
      var52.setTargetSubsystem(this.Uv(this.RF.KT));
      var52.setEndianness(this.Dw(this.q.RF));
      var52.setWordSize(this.Dw ? 64 : 32);
      var52.setCompilationTimestamp(this.q.getTimestampMs());
      var52.setImageBase(this.RF.za);
      var52.setImageSize(this.RF.PV);
      var52.setEntryPoint(this.RF.gO);
      var52.setOverlayOffset(var1);
      this.setLoaderInformation(var52.build());

      for (int var5 = 0; var5 < this.RF.Dz.length; var5++) {
         eo var59 = this.RF.Dz[var5];
         String var69 = var5 < eo.q.length ? eo.q[var5] : "-";
         long var73 = 0L;
         if (var59.xK != 0L) {
            var73 = this.convertRelativeAddressToFileOffset(var59.xK);
         }

         this.addSection(new SegmentInformation(var69, var73, var59.Dw, var59.xK, var59.Dw, 0));
      }

      if ((this.q.nf & 2) != 0) {
         Assert.a(this.getRawMemoryMappedImage() != null);

         try {
            this.Uv();
         } catch (MemoryException var44) {
            this.logError(true, S.L("Unable to process Exports"));
            JebCoreService.notifySilentExceptionToClient(new JebRuntimeException("exports processing failed"));
         }

         try {
            this.Dw();
         } catch (MemoryException var43) {
            this.logError(true, S.L("Unable to process Imports"));
            JebCoreService.notifySilentExceptionToClient(new JebRuntimeException("imports processing failed"));
         }

         try {
            this.nf();
         } catch (MemoryException var42) {
            this.logError(true, S.L("Unable to process debug info"));
            JebCoreService.notifySilentExceptionToClient(new JebRuntimeException("debug info processing failed"));
         }

         try {
            this.oW();
         } catch (MemoryException var41) {
            this.logError(true, S.L("Unable to process Thread Local Storage"));
            JebCoreService.notifySilentExceptionToClient(new JebRuntimeException("TLS processing failed"));
         }

         try {
            this.gO();
         } catch (MemoryException var40) {
            this.logError(true, S.L("Unable to parse Rich header"));
            JebCoreService.notifySilentExceptionToClient(new JebRuntimeException("rich header processing failed"));
         }

         boolean var55 = true;
         if (var55) {
            try {
               String var70 = q(this.q);
               IUnit var60;
               if (var70 != null) {
                  var60 = this.getUnitProcessor().process(var70 + " image", this.getInput(), this, var70, true, true);
               } else {
                  var60 = this.getUnitProcessor().process(null, this.getInput(), this, "code_disa", true, true);
               }

               if (var60 != null) {
                  this.addChild(var60);
               } else {
                  this.logError(true, S.L("No matching disassembler found."));
               }
            } catch (Exception var39) {
               lm.catching(var39);
               this.addNotification(new UnitNotification(NotificationType.UNSUPPORTED_FEATURE, S.L("The machine code was not disassembled")));
            }
         }
      }

      if (this.RF.Dz.length > 2) {
         eo var56 = this.RF.Dz[2];
         if (var56.xK > 0L && var56.Dw > 0L) {
            cqg var61 = new cqg(this.getRawMemoryMappedImage(), this.RF.za + var56.xK, var56.Dw);

            try {
               var61.oW();
               int var71 = var61.Uv();
               if (var71 > 3) {
                  this.logWarn(true, S.L("PE uses a non-standard type/name/language resource tree; max tree depth is %d when It should be 3"), var71);
               }

               if (var61.RF() && !var61.xK()) {
                  this.logWarn(true, S.L("PE has a resource tree but does not contain resource data"));
               }

               if (var61.xK()) {
                  ContainerUnit var74 = new ContainerUnit("Resources", this.getUnitProcessor(), this, this.getPropertyDefinitionManager());
                  var74.process();
                  this.addChild(var74);
                  StringBuilder var78 = new StringBuilder();
                  cqh var80 = new cqh();
                  cqf var84 = var61.q();

                  for (cqf var94 : var84.oW()) {
                     if (!var94.gO()) {
                        String var97 = var61.q(var94);
                        String var100 = var61.RF(var94);
                        ContainerUnit var102 = new ContainerUnit(var100, var74.getUnitProcessor(), var74, var74.getPropertyDefinitionManager());
                        var102.process();
                        var74.addChild(var102);

                        for (cqe var105 : var94.gP()) {
                           String var19 = var61.RF(var105.q());
                           byte[] var107 = new byte[var105.xK()];
                           this.getRawMemoryMappedImage().read(this.RF.za + var105.RF(), var105.xK(), var107, 0);
                           IUnit var21 = this.getUnitProcessor().process(var19, new BytesInput(var107), var102);
                           if (var21 != null) {
                              var102.addChild(var21);
                           } else {
                              this.logError(true, S.L("Cannot parse Resource entry \"%s\""), var19);
                           }

                           if (var61.q(var94).equals("VERSION")) {
                              try {
                                 cqi var108 = new cqi(var107);
                                 String var23 = var108.q();
                                 if (var78.length() > 0) {
                                    var78.append(Strings.generate('-', 80) + "\n");
                                 }

                                 var78.append(var23);
                              } catch (Exception var38) {
                                 lm.catching(var38);
                              }
                           } else if (!var97.equals("ICON") && !var97.equals("GROUP_ICON") && var97.equals("STRING")) {
                              try {
                                 var80.q(var105, var107);
                              } catch (Exception var37) {
                                 lm.catching(var37);
                              }
                           }
                        }
                     }
                  }

                  if (var78.length() > 0) {
                     IUnit var90 = this.getUnitProcessor().process("versions.txt", new BytesInput(Strings.encodeUTF8(var78.toString())), var74);
                     var74.addChild(var90);
                  }

                  var80.q(var74);
               }
            } catch (Exception var48) {
               lm.catching(var48);
               this.logError(true, S.L("Resources processing failed! (%s)"), var48.toString());
               JebCoreService.notifySilentExceptionToClient(var48);
            }
         }
      }

      if (this.RF.Dz.length > 4) {
         eo var57 = this.RF.Dz[4];
         long var62 = var57.xK;
         long var75 = this.getInput().getCurrentSize();
         if (var62 > 0L && var62 < var75 && var57.Dw > 0L && var57.Dw < var75 && var62 + var57.Dw <= var75) {
            cpw var81 = new cpw(this.getInput(), (int)var57.xK, (int)var57.Dw);

            try {
               try {
                  var81.xK();
               } catch (Exception var36) {
                  lm.catching(var36);
               }

               if (var81.q()) {
                  ContainerUnit var85 = new ContainerUnit("Certificates", this.getUnitProcessor(), this, this.getPropertyDefinitionManager());
                  var85.process();
                  this.addChild(var85);
                  byte var91 = 0;

                  for (cpv var98 : var81.RF()) {
                     String var101 = var98.Dw();
                     String var103 = var98.Uv();
                     String var104 = Strings.ff("Certificate #%d (%s, %s)", Integer.valueOf(var91), var101, var103);
                     IUnit var106 = this.getUnitProcessor().process(var104, new BytesInput(var98.xK()), var85);
                     if (var106 != null) {
                        var85.addChild(var106);
                     } else {
                        this.logError(true, S.L("Cannot parse Certificate entry \"%s\""), var104);
                     }
                  }
               }
            } catch (Exception var47) {
               lm.catching(var47);
               this.logError(true, S.L("Certificates processing failed! (%s)"), var47.toString());
               JebCoreService.notifySilentExceptionToClient(var47);
            }
         }
      }

      if (this.xK != null && this.xK.length > 0) {
         try (SeekableByteChannel var58 = this.getInput().getChannel()) {
            long var63 = var58.size();
            if (var63 > var1) {
               this.gO = true;
               long var76 = var63 - var1;
               SubInput var82 = new SubInput(this.getInput(), var1, var76);
               IUnit var86 = this.getUnitProcessor().process("Appended Data", var82, this);
               this.addChild(var86);
            }
         } catch (IOException var46) {
            lm.catching(var46);
         }
      }

      return true;
   }

   private boolean q(SeekableByteChannel var1) {
      if (this.q.Uv != 0 && this.q.oW != 0) {
         com.pnfsoftware.jeb.corei.parsers.wincoff.CU[] var2 = new com.pnfsoftware.jeb.corei.parsers.wincoff.CU[this.q.oW];
         int var3 = 0;

         try {
            var1.position(this.q.Uv);
            ByteBuffer var4 = ByteBuffer.allocate(18).order(ByteOrder.LITTLE_ENDIAN);

            for (int var5 = 0; var5 < var2.length; var5++) {
               var4.rewind();
               if (var1.read(var4) != 18) {
                  return false;
               }

               var4.rewind();
               if (var3 == 0) {
                  com.pnfsoftware.jeb.corei.parsers.wincoff.CU var6 = com.pnfsoftware.jeb.corei.parsers.wincoff.CU.q(var4);
                  var2[var5] = var6;
                  var3 = var6.qa;
                  String var7 = this.q(var2[var5].zz);
                  if (var6.LK == 776 && (var6.io == 3 || var6.io == 2) && var6.HF > 0) {
                     ISegmentInformation var8 = this.getSegment(var6.HF);
                     SymbolInformation var9 = new SymbolInformation(SymbolType.UNKNOWN, 0, 0L, var7, 0L, var8.getOffsetInMemory() + var6.JY, 1L);
                     this.addSymbol(var9);
                  }
               } else {
                  var3--;
                  Object[] var10000 = new Object[0];
               }
            }

            return true;
         } catch (Exception var10) {
            this.logError(true, S.L("Golang COFF symbol parsing failed (%s)"), var10.toString());
            JebCoreService.notifySilentExceptionToClient(var10, Maps.toMap("reason", "Golang COFF symbol parsing failed"));
            return false;
         }
      } else {
         return false;
      }
   }

   private String q(byte[] var1) {
      String var2;
      if (var1[0] == 0 && var1[1] == 0 && var1[2] == 0 && var1[3] == 0) {
         int var5 = EndianUtil.littleEndianBytesToInt(var1, 4);
         var2 = this.q(var5);
      } else {
         try {
            int var3 = 0;

            while (var3 < var1.length && var1[var3] != 0) {
               var3++;
            }

            var2 = Strings.decodeASCII(var1, 0, var3);
         } catch (Exception var4) {
            var2 = "";
         }
      }

      return var2;
   }

   private String q(int var1) {
      Object var2 = null;

      try {
         Object var12;
         try (SeekableByteChannel var3 = this.getInput().getChannel()) {
            var3.position(this.q.Uv + this.q.oW * 18 + var1);
            int var4 = 1;
            ByteBuffer var5 = ByteBuffer.allocate(var4);
            if (var3.read(var5) != -1) {
               byte[] var6 = var5.array();

               while (var6[var6.length - 1] != 0) {
                  var5 = ByteBuffer.allocate(++var4);
                  var3.position(this.q.Uv + this.q.oW * 18 + var1);
                  if (var3.read(var5) == -1) {
                     return null;
                  }

                  var6 = var5.array();
               }

               if (var6.length == 1) {
                  return "";
               }

               return Strings.decodeASCII(var6, 0, var4 - 1);
            }

            var12 = null;
         }

         return (String)var12;
      } catch (IOException var10) {
         Object[] var10000 = new Object[]{var10};
         return (String)var2;
      }
   }

   private static String q(nI var0) {
      switch (var0.RF) {
         case 332:
            return "x86";
         case 352:
         case 354:
         case 358:
         case 360:
         case 361:
         case 870:
            return "mips";
         case 448:
         case 450:
         case 452:
            return "arm";
         case 34404:
            return "x86_64";
         case 43620:
            return "arm64";
         default:
            return null;
      }
   }

   private void Dw() throws MemoryException {
      if (this.RF.Dz.length > 1) {
         if (this.RF.Dz[1].xK != 0L) {
            IVirtualMemory var1 = this.getRawMemoryMappedImage();
            ArrayList var2 = new ArrayList();
            long var3 = this.RF.za;
            long var5 = this.q(var3, this.RF.Dz[1].xK);
            if (this.RF(var5)) {
               this.gP = ej.q(var5, var1, var3);
               if (this.gP != null) {
                  HashMap var7 = new HashMap();

                  for (oM var9 : this.gP.q) {
                     var9.za = var9.RF != 0L && var1.check(var3 + var9.RF, 1, 1) != 0;
                     String var10 = this.q(var1, var3 + var9.Uv);
                     cpx var11 = cpz.q(var10);
                     Object[] var10000 = new Object[]{Strings.truncate(var10, 100)};
                     int var12 = this.Dw ? 8 : 4;
                     long var13 = this.Dw ? Long.MIN_VALUE : 2147483648L;
                     long var15 = var9.RF;
                     long var17 = var9.oW;
                     int var19 = 0;
                     long var20 = 0L;

                     while (true) {
                        long var22;
                        if (var9.za) {
                           var22 = this.Dw ? var1.readLELong(var3 + var15) : var1.readLEInt(var3 + var15) & 4294967295L;
                        } else {
                           var22 = this.Dw ? var1.readLELong(var3 + var17) : var1.readLEInt(var3 + var17) & 4294967295L;
                        }

                        if (var22 == 0L) {
                           var9.gP = var19;
                           var9.lm = var20;
                           break;
                        }

                        String var25 = null;
                        int var24;
                        if ((var22 & var13) != 0L) {
                           var24 = (int)var22 & 65535;
                           if (var11 != null) {
                              var25 = var11.q(var24);
                           }

                           if (var25 == null) {
                              var25 = "#" + var24;
                           }
                        } else {
                           var24 = var1.readLEShort(var3 + var22) & '\uffff';
                           var25 = this.q(var1, var3 + var22 + 2L);
                           if (var25 != null) {
                              var20 = var3 + var22 + 2L + var25.length();
                           }
                        }

                        var10000 = new Object[]{var24, Strings.truncate(var25, 100)};
                        String var26 = var10 + "!" + var25;
                        SymbolInformation var27 = new SymbolInformation(SymbolType.PTRFUNCTION, 1, var24, var26, 0L, var17, var12);
                        var2.add(var27);
                        var7.put(var26, var27);
                        var15 += var12;
                        var17 += var12;
                        var19++;
                     }
                  }

                  this.oW = this.RF.Dz[1].Dw >= 40L && var7.containsKey("mscoree.dll!_CorExeMain");
                  this.addAllSymbols(var2);
               }
            }
         }
      }
   }

   private void Uv() throws MemoryException {
      if (this.RF.Dz.length > 0) {
         if (this.RF.Dz[0].xK != 0L) {
            IVirtualMemory var1 = this.getRawMemoryMappedImage();
            ArrayList var2 = new ArrayList();
            long var3 = this.RF.za;
            long var5 = this.q(var3, this.RF.Dz[0].xK);
            if (this.RF(var5)) {
               long var7 = this.RF.Dz[0].xK;
               long var9 = var7 + this.RF.Dz[0].Dw;
               this.nf = CU.q(var5, var1, var3);
               if (this.nf != null) {
                  Object[] var10000 = new Object[]{Strings.truncate(Strings.safe(this.nf.zz, "-"), 100)};
                  int var11 = this.nf.gO;

                  for (long var12 = this.nf.gP; var11 > 0; var11--) {
                     long var14 = var1.readLEInt(var3 + var12) & 4294967295L;
                     SymbolType var16 = SymbolType.FUNCTION_MAYBE;
                     if (var14 >= var7 && var14 < var9) {
                        this.q(var1, var3 + var14);
                        var16 = SymbolType.FORWARDED_FUNCTION;
                     }

                     SymbolInformation var17 = new SymbolInformation(var16, 2, 0L, null, 0L, var14, 0L);
                     var2.add(var17);
                     var12 += 4L;
                  }

                  String var27 = "";
                  boolean var15 = true;
                  int var28 = this.nf.nf;
                  long var29 = this.nf.lm;

                  for (long var19 = this.nf.za; var28 > 0; var28--) {
                     int var21 = var1.readLEShort(var3 + var29) & '\uffff';
                     var29 += 2L;
                     int var22 = this.nf.oW + var21;
                     long var23 = var1.readLEInt(var3 + var19) & 4294967295L;
                     var19 += 4L;
                     String var25 = this.q(var1, var3 + var23);
                     if (var15 && var25.compareTo(var27) < 0) {
                        var15 = false;
                     }

                     if (var21 < var2.size()) {
                        SymbolInformation var26 = (SymbolInformation)var2.get(var21);
                        var26.setName(var25);
                        var26.setIdentifier(var22);
                     }

                     var27 = var25;
                  }

                  if (!var15) {
                     this.addNotification(new UnitNotification(NotificationType.ERROR, S.L("PE export names are out of order")));
                  }

                  this.addAllSymbols(var2);
               }
            }
         }
      }
   }

   private void oW() throws MemoryException {
      if (this.RF.Dz.length >= 10) {
         eo var1 = this.RF.Dz[9];
         if (var1.xK != 0L) {
            IVirtualMemory var2 = this.getRawMemoryMappedImage();
            ArrayList var3 = new ArrayList();
            long var4 = this.RF.za;
            long var6 = this.q(var4, var1.xK);
            if (this.RF(var6)) {
               if (this.Dw) {
                  var6 = var2.readLELong(var6 + 24L);
                  if (var6 != 0L) {
                     while (true) {
                        long var8 = var2.readLELong(var6);
                        if (var8 == 0L) {
                           break;
                        }

                        var3.add((int)(var8 - var4));
                        var6 += 8L;
                     }
                  }
               } else {
                  var6 = var2.readLEInt(var6 + 12L) & 4294967295L;
                  if (var6 != 0L) {
                     while (true) {
                        long var16 = var2.readLEInt(var6) & 4294967295L;
                        if (var16 == 0L) {
                           break;
                        }

                        var3.add((int)(var16 - var4));
                        var6 += 4L;
                     }
                  }
               }

               ArrayList var17 = new ArrayList();
               int var9 = 0;

               for (int var11 : var3) {
                  String var12 = "__tls_callback_" + var9;
                  SymbolInformation var13 = new SymbolInformation(SymbolType.FUNCTION, 2, 0L, var12, 0L, var11, 0L);
                  var17.add(var13);
                  var9++;
               }

               this.addAllSymbols(var17);
            }
         }
      }
   }

   private void gO() throws MemoryException {
      PY var1 = new PY(this.getRawMemoryMappedImage(), this.RF.za);
      if (var1.q()) {
         IUnit var2 = this.getUnitProcessor().process("Rich header", new BytesInput(Strings.encodeUTF8(var1.toString())), this);
         this.addChild(var2);
      }
   }

   private void nf() throws MemoryException {
      if (this.RF != null && this.RF.Dz.length > 6) {
         eo var1 = this.RF.Dz[6];
         long var2 = this.RF.getImageBase();
         long var4 = var2 + var1.getPosition();
         long var6 = var1.getSize();
         IVirtualMemory var8 = this.getRawMemoryMappedImage();
         this.za = new ArrayList();
         long var9 = var6;

         for (byte var11 = 28; var9 >= var11; var4 += var11) {
            byte[] var12 = new byte[var11];
            int var13 = var8.read(var4, var11, var12, 0);
            if (var13 != var11) {
               break;
            }

            CoffDebugDirectoryEntry var14 = CoffDebugDirectoryEntry.parse(var4, ByteBufferUtil.wrapLE(var12));
            this.za.add(var14);
            var9 -= var11;
         }
      }
   }

   private String q(IVirtualMemory var1, long var2) {
      short var4 = 256;
      byte[] var5 = new byte[var4];
      int var6 = 0;
      int var7 = 0;

      label32:
      while (true) {
         int var8 = VirtualMemoryUtil.readBytesSafe(var1, var2 + var7, var5.length - var7, var5, var7, 1);
         if (var8 == -1) {
            break;
         }

         for (var6 += var8; var7 < var6; var7++) {
            if (var5[var7] == 0) {
               break label32;
            }
         }

         if (var6 != var5.length) {
            break;
         }

         var4 *= 2;
         var5 = Arrays.copyOf(var5, var4);
      }

      try {
         return new String(var5, 0, var7, "US-ASCII");
      } catch (UnsupportedEncodingException var9) {
         lm.catching(var9);
         return "";
      }
   }

   private int RF(int var1) {
      int var2 = 0;
      var2 |= (var1 & 1073741824) != 0 ? 2 : 0;
      var2 |= (var1 & -2147483648) != 0 ? 1 : 0;
      return var2 | ((var1 & 536870912) != 0 ? 4 : 0);
   }

   private ProcessorType xK(int var1) {
      switch (var1) {
         case 332:
            return ProcessorType.X86;
         case 352:
            return ProcessorType.MIPS;
         case 354:
         case 358:
         case 360:
         case 361:
         case 870:
            return ProcessorType.MIPS;
         case 448:
         case 450:
         case 452:
            return ProcessorType.ARM;
         case 34404:
            return ProcessorType.X86_64;
         case 43620:
            return ProcessorType.ARM64;
         default:
            return null;
      }
   }

   private Endianness Dw(int var1) {
      switch (var1) {
         case 352:
            return Endianness.BIG_ENDIAN;
         default:
            return Endianness.LITTLE_ENDIAN;
      }
   }

   private SubsystemType Uv(int var1) {
      switch (var1) {
         case 2:
         case 3:
            return SubsystemType.WINDOWS_USER;
         case 8:
            return SubsystemType.WINDOWS_KERNEL;
         default:
            return null;
      }
   }

   public long q(long var1, long var3) {
      return this.q(var1 + var3);
   }

   public long q(long var1) {
      return this.Dw ? var1 : var1 & 4294967295L;
   }

   public boolean RF(long var1) {
      return var1 >= this.RF.za && var1 < this.q(this.RF.za, this.RF.PV);
   }

   @Override
   public long convertFileOffsetToRelativeAddress(long var1) {
      for (iZ var6 : this.xK) {
         if (var1 >= var6.oW && var1 <= var6.oW + var6.Uv) {
            return var6.Dw + (var1 - var6.oW);
         }
      }

      return -1L;
   }

   @Override
   public long convertRelativeAddressToFileOffset(long var1) {
      for (iZ var6 : this.xK) {
         if (var1 >= var6.Dw && var1 < var6.Dw + var6.xK) {
            return var6.oW + (var1 - var6.Dw);
         }
      }

      return -1L;
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      if (this.isProcessed()) {
         var1.append("\nNotes:\n");
         if (this.Dw) {
            var1.append("- " + S.L("This is a 64-bit PE file\n"));
         }

         if (this.gO) {
            var1.append("- " + S.L("This PE file contains appended data (unmapped)\n"));
         }

         if (this.oW) {
            var1.append("- " + S.L("This PE file is a .NET executable\n"));
         }

         if (this.Uv) {
            var1.append("- " + S.L("This PE file has low-alignment values\n"));
         }

         if (this.RF.JY >= 10) {
            Strings.ff(var1, "- " + S.L("The raw timeDateStamp value is 0x%08X (Win10+: may be a reproducible build id)\n"), this.q.Dw);
         }
      }

      return var1.toString();
   }

   @Override
   public IUnitFormatter getFormatter() {
      return super.getFormatter();
   }

   @Override
   public ICOFFHeader getCOFFHeader() {
      return this.q;
   }

   @Override
   public IPEOptionalHeader getPEOptionalHeader() {
      return this.RF;
   }

   @Override
   public ICOFFSectionHeader[] getSectionHeaders() {
      return this.xK;
   }

   @Override
   protected boolean shouldAllocateFullImage() {
      return true;
   }

   @Override
   protected boolean applyRelocations(IVirtualMemory var1, long var2, ILinkInfoProvider var4) {
      if (this.RF.Dz.length < 5) {
         return true;
      } else {
         eo var5 = this.RF.Dz[5];
         if (var5.xK != 0L && var5.Dw != 0L) {
            long var6 = this.RF.za;
            long var8 = var2 - var6;
            if (var8 == 0L) {
               return true;
            } else {
               int var10 = (int)var8;
               long var11 = var8;
               HashSet var13 = new HashSet();

               try {
                  long var14 = var2 + var5.xK;
                  long var16 = var5.Dw;

                  while (var16 >= 8L) {
                     int var18 = var1.readInt(var14);
                     int var19 = var1.readInt(var14 + 4L);
                     if (var16 < var19 || var19 < 8) {
                        throw new JebRuntimeException(Strings.ff("Bad BaseRelocation block at RVA 0x%X", var14 - var2));
                     }

                     long var20 = var2 + (var18 & 4294967295L);
                     byte[] var22 = new byte[(var19 - 8) / 2 * 2];
                     var1.read(var14 + 8L, var22.length, var22, 0);
                     ByteBuffer var23 = ByteBuffer.wrap(var22);
                     var23.order(ByteOrder.LITTLE_ENDIAN);
                     int var24 = var22.length / 2;

                     while (var24-- > 0) {
                        int var25 = var23.getShort() & '\uffff';
                        int var26 = var25 >>> 12;
                        int var27 = var25 & 4095;
                        long var28 = var20 + var27;
                        switch (var26) {
                           case 0:
                              break;
                           case 1:
                              RF(var1, var28, var10);
                              break;
                           case 2:
                              q(var1, var28, var10);
                              break;
                           case 3:
                              xK(var1, var28, var10);
                              break;
                           case 4:
                           case 5:
                           case 6:
                           case 7:
                           case 8:
                           case 9:
                           default:
                              var13.add(var26);
                              break;
                           case 10:
                              q(var1, var28, var11);
                        }
                     }

                     var14 += var19;
                     var16 -= var19;
                  }

                  if (!var13.isEmpty()) {
                     throw new JebRuntimeException("Unsupported relocation types: " + var13);
                  } else {
                     return true;
                  }
               } catch (Exception var30) {
                  JebRuntimeException var15 = new JebRuntimeException("Cannot apply relocations", var30);
                  JebCoreService.notifySilentExceptionToClient(var15);
                  return false;
               }
            }
         } else {
            return true;
         }
      }
   }

   private static void q(IVirtualMemory var0, long var1, int var3) throws MemoryException {
      var0.writeShort(var1, (short)(var0.readShort(var1) + (var3 & 65535)));
   }

   private static void RF(IVirtualMemory var0, long var1, int var3) throws MemoryException {
      var0.writeShort(var1, (short)(var0.readShort(var1) + (var3 >>> 16)));
   }

   private static void xK(IVirtualMemory var0, long var1, int var3) throws MemoryException {
      var0.writeInt(var1, var0.readInt(var1) + var3);
   }

   private static void q(IVirtualMemory var0, long var1, long var3) throws MemoryException {
      var0.writeLong(var1, var0.readLong(var1) + var3);
   }

   public CU q() {
      return this.nf;
   }

   public ej RF() {
      return this.gP;
   }

   public List xK() {
      return this.za;
   }
}
