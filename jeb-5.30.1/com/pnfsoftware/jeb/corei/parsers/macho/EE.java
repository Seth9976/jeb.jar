package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.input.SubInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.NotificationType;
import com.pnfsoftware.jeb.core.units.UnitNotification;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.codeobject.AbstractCodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ILinkInfoProvider;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.core.units.codeobject.LoaderInformation;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SegmentInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolType;
import com.pnfsoftware.jeb.util.encoding.IntegerLEB128;
import com.pnfsoftware.jeb.util.encoding.LongLEB128;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ChannelUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.ckb;
import com.pnfsoftware.jebglobal.ckc;
import com.pnfsoftware.jebglobal.ckd;
import com.pnfsoftware.jebglobal.cke;
import com.pnfsoftware.jebglobal.ckf;
import com.pnfsoftware.jebglobal.ckg;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.List;

@Ser
public class EE extends AbstractCodeObjectUnit {
   private static final ILogger Me = GlobalLog.getLogger(EE.class);
   @SerId(1)
   tw q;
   @SerId(2)
   List RF = new ArrayList();
   @SerId(3)
   boolean xK;
   @SerId(4)
   Endianness Dw;
   @SerTransient
   long Uv;
   @SerTransient
   long oW;
   @SerTransient
   long gO;
   @SerTransient
   long nf;
   @SerTransient
   long gP;
   @SerTransient
   boolean za;
   @SerTransient
   List lm;
   @SerTransient
   List zz;
   @SerTransient
   List JY;
   @SerTransient
   List HF;
   @SerTransient
   List LK;
   @SerTransient
   boolean io = true;
   @SerTransient
   boolean qa = false;
   @SerTransient
   boolean Hk = true;

   public EE(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(var2, "macho", var1, var3, var4, var5);
   }

   private void q() {
      this.Uv = -1L;
      this.oW = -1L;
      this.gO = -1L;
      this.nf = 0L;
      this.gP = 0L;
      this.za = false;
      this.lm = new ArrayList();
      this.zz = new ArrayList();
      this.JY = new ArrayList();
      this.HF = new ArrayList();
      this.LK = new ArrayList();
   }

   @Override
   public boolean canBePersisted() {
      return true;
   }

   @Override
   protected boolean processInternal() {
      this.q();

      try (SeekableByteChannel var1 = this.getInput().getChannel()) {
         int var2 = ChannelUtil.getLEInt(var1, 0L);
         var1.position(0L);
         this.xK = var2 == -17958193 || var2 == -805638658;
         this.Dw = var2 != -17958194 && var2 != -17958193 ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN;
         int var3 = this.xK ? 32 : 28;
         ByteBuffer var4 = ByteBuffer.allocate(var3).order(this.Dw.toByteOrder());
         if (var1.read(var4) != var3) {
            return false;
         }

         var4.rewind();
         this.q = tw.q(var4, this.xK);
         if (this.q.Dw != 2 && this.q.Dw != 6 && this.q.Dw != 8) {
            throw new RuntimeException(Strings.ff("TBI: filetype %d", this.q.Dw));
         }

         for (int var5 = 0; var5 < this.q.Uv; var5++) {
            long var6 = var1.position();
            int var8 = ChannelUtil.getInt(var1, var6, this.Dw.toByteOrder());
            int var9 = ChannelUtil.getInt(var1, var6 + 4L, this.Dw.toByteOrder());
            if (this.xK && var9 % 8 != 0 || !this.xK && var9 % 4 != 0) {
               Object[] var67 = new Object[0];
            }

            var1.position(var6);
            var4 = ByteBuffer.allocate(var9).order(this.Dw.toByteOrder());
            if (var1.read(var4) != var9) {
               return false;
            }

            var4.rewind();
            label386:
            switch (var8) {
               case -2147483614:
               case 34:
                  eo var62 = eo.q(var4);
                  if (!this.q(var62, this.qa, false)) {
                     return false;
                  }

                  if (!this.q(var62, this.qa, true)) {
                     return false;
                  }

                  if (!this.RF(var62, this.qa)) {
                     return false;
                  }

                  if (!this.q(var62, this.qa)) {
                     return false;
                  }

                  this.za = true;
                  this.RF.add(var62);
                  break;
               case -2147483608:
                  nI var51 = nI.q(var4);
                  if (this.oW != -1L) {
                     throw new RuntimeException("TBI: multiple entry points");
                  }

                  this.oW = var51.xK;
                  this.RF.add(var51);
                  break;
               case 1:
                  if (this.xK) {
                     Object[] var77 = new Object[0];
                     return false;
                  }

                  oL var10 = oL.q(var4);
                  if (var10.Uv % 4096 != 0) {
                     var10.Uv &= -4096;
                     var10.Uv += 4096;
                  }

                  if (var10.gO % 4096 != 0) {
                     var10.gO &= -4096;
                     var10.gO += 4096;
                  }

                  int var11 = this.xK(var10.gP);
                  String var12 = Strings.decodeASCII(var10.xK).trim();
                  SegmentInformation var13 = new SegmentInformation(var12, var10.oW, var10.gO, var10.Dw - this.nf, var10.Uv, var11);
                  Object[] var73 = new Object[]{var13};
                  if (var12.equals("__PAGEZERO")) {
                     var13.setFlags(var11 | 1073741824);
                  }

                  this.addSegment(var13);
                  this.gP = Longs.maxUnsigned(var10.Dw + var10.Uv & 4294967295L, this.gP);
                  if (var12.equals("__TEXT")) {
                     if (this.Uv != -1L) {
                        throw new RuntimeException("TBI: multiple __TEXT segments");
                     }

                     this.Uv = var10.Dw & 4294967295L;
                  }

                  if (var10.za > 255) {
                     this.addNotification(
                        new UnitNotification(NotificationType.WARNING, Strings.ff(S.L("Mach-O file: number of sections is more than %d (%d)"), 255, var10.za))
                     );
                  }

                  for (int var44 = 0; var44 < var10.za; var44++) {
                     PY var46 = PY.q(var4);
                     if ((var46.gP & 0xFF) == 6) {
                        this.JY.add(var46);
                     }

                     if ((var46.gP & 0xFF) == 7) {
                        this.zz.add(var46);
                     }

                     String var47 = Strings.ff("%s", Strings.decodeASCII(var46.q).trim());
                     SegmentInformation var49 = new SegmentInformation(var47, var46.Uv, var46.Dw, var46.xK - this.nf, var46.Dw, var11);
                     this.addSection(var49);
                     var73 = new Object[]{var49};
                     if (this.q(var46, var47, var12)) {
                        var73 = new Object[0];
                        this.HF.add(var49);
                     }

                     if (this.Hk && ckb.RF(var46, var47)) {
                        var73 = new Object[0];
                        this.LK.add(var46);
                     }
                  }

                  if (var12.equals("__TEXT")) {
                     long var45 = this.getSection(0).getOffsetInMemory() - this.Uv;
                     SegmentInformation var48 = new SegmentInformation("<hdr>", 0L, var45, this.Uv, var45, 3);
                     this.insertSection(0, var48);
                     this.HF.add(var48);
                  }

                  this.RF.add(var10);
                  break;
               case 2:
                  Bu var60 = Bu.q(var4);
                  if (!this.q(var60, this.qa)) {
                     return false;
                  }

                  this.RF.add(var60);
                  break;
               case 4:
               case 5:
                  switch (this.q.RF) {
                     case 7:
                        Nz var53 = Nz.q(var4);
                        if (var53.xK != 1) {
                           throw new RuntimeException(Strings.ff("TBI: x86 flavor %d", var53.xK));
                        }

                        this.gO = var53.LK & 4294967295L;
                        this.RF.add(var53);
                        break label386;
                     case 12:
                        bK var58 = bK.q(var4);
                        if (var58.xK != 1) {
                           throw new RuntimeException(Strings.ff("TBI: ARM32 flavor %d", var58.xK));
                        }

                        this.gO = var58.nf & 4294967295L;
                        this.RF.add(var58);
                        break label386;
                     case 16777223:
                        Uz var56 = Uz.q(var4);
                        if (var56.xK != 4) {
                           throw new RuntimeException(Strings.ff("TBI: x86-64 flavor %d", var56.xK));
                        }

                        this.gO = var56.oQ;
                        this.RF.add(var56);
                        break label386;
                     case 16777228:
                        tl var59 = tl.q(var4);
                        if (var59.xK != 6) {
                           throw new RuntimeException(Strings.ff("TBI: ARM64 flavor %d", var59.xK));
                        }

                        this.gO = var59.gP;
                        this.RF.add(var59);
                        break label386;
                     default:
                        Me.error(S.L("NOT IMPLEMENTED: LC_UNIXTHREAD with %s cpu type"), this.q.RF);
                        break label386;
                  }
               case 11:
                  CU var27 = CU.q(var4);
                  if (!this.za && !this.q(var27, this.qa)) {
                     return false;
                  }

                  this.RF.add(var27);
                  break;
               case 25:
                  if (!this.xK) {
                     Object[] var72 = new Object[0];
                     return false;
                  }

                  Vj var14 = Vj.q(var4);
                  if (var14.Uv % 4096L != 0L) {
                     var14.Uv &= -4096L;
                     var14.Uv += 4096L;
                  }

                  if (var14.gO % 4096L != 0L) {
                     var14.gO &= -4096L;
                     var14.gO += 4096L;
                  }

                  int var15 = this.xK(var14.gP);
                  String var16 = Strings.decodeASCII(var14.xK).trim();
                  SegmentInformation var17 = new SegmentInformation(var16, var14.oW, var14.gO, var14.Dw - this.nf, var14.Uv, var15);
                  Object[] var68 = new Object[]{var17};
                  if (var16.equals("__PAGEZERO")) {
                     var17.setFlags(var15 | 1073741824);
                  }

                  this.addSegment(var17);
                  this.gP = Longs.maxUnsigned(var14.Dw + var14.Uv, this.gP);
                  if (var16.equals("__TEXT")) {
                     if (this.Uv != -1L) {
                        throw new RuntimeException("TBI: multiple __TEXT segments");
                     }

                     this.Uv = var14.Dw;
                  }

                  if (var14.za > 255) {
                     this.addNotification(
                        new UnitNotification(NotificationType.WARNING, Strings.ff(S.L("Mach-O file: number of sections is more than %d (%d)"), 255, var14.za))
                     );
                  }

                  for (int var18 = 0; var18 < var14.za; var18++) {
                     vb var52 = vb.q(var4);
                     if ((var52.gP & 0xFF) == 6) {
                        this.JY.add(var52);
                     }

                     if ((var52.gP & 0xFF) == 7) {
                        this.zz.add(var52);
                     }

                     String var54 = Strings.ff("%s", Strings.decodeASCII(var52.q).trim());
                     SegmentInformation var57 = new SegmentInformation(var54, var52.Uv, var52.Dw, var52.xK - this.nf, var52.Dw, var15);
                     this.addSection(var57);
                     var68 = new Object[]{var57};
                     if (this.q(var52, var54, var16)) {
                        var68 = new Object[0];
                        this.HF.add(var57);
                     }

                     if (this.Hk && ckb.RF(var52, var54)) {
                        var68 = new Object[0];
                        this.LK.add(var52);
                     }
                  }

                  if (var16.equals("__TEXT")) {
                     long var50 = this.getSection(0).getOffsetInMemory() - this.Uv;
                     SegmentInformation var55 = new SegmentInformation("<hdr>", 0L, var50, this.Uv, var50, 3);
                     this.insertSection(0, var55);
                     this.HF.add(var55);
                  }

                  this.RF.add(var14);
                  break;
               case 38:
                  Nt var19 = Nt.q(var4);
                  SubInput var20 = new SubInput(this.getInput(), var19.xK, var19.Dw);
                  InputStream var21 = var20.getStream();
                  long var22 = 0L;

                  while (true) {
                     long var24;
                     try {
                        var24 = LongLEB128.readULEB128(var21);
                     } catch (IOException var33) {
                        break;
                     }

                     if (var24 == 0L) {
                        break;
                     }

                     var22 += var24;
                     SymbolInformation var26 = new SymbolInformation(SymbolType.FUNCTION, 0, 0L, null, 0L, var22 + this.Uv - this.nf, 0L);
                     this.addSymbol(var26);
                  }

                  var21.close();
                  this.RF.add(var19);
                  break;
               default:
                  kY var29 = kY.q(var4);
                  this.RF.add(var29);
            }

            var1.position(var6 + var9);
         }

         if (this.Hk) {
            try {
               if (!this.RF()) {
                  this.addNotification(new UnitNotification(NotificationType.WARNING, S.L("Problem during Objective-C parsing")));
               }
            } catch (IOException var32) {
               this.addNotification(new UnitNotification(NotificationType.WARNING, S.L("Problem during Objective-C parsing")));
            }
         }
      } catch (IOException var35) {
         Object[] var10000 = new Object[]{var35};
         return false;
      }

      if (this.gP == -1L) {
         Object[] var78 = new Object[0];
         return false;
      } else {
         LoaderInformation.Builder var36 = new LoaderInformation.Builder();
         var36.setFlags(0);
         var36.setTargetProcessor(this.RF(this.q.RF));
         var36.setEndianness(this.Dw);
         var36.setWordSize(this.xK ? 64 : 32);
         var36.setImageBase(this.nf);
         var36.setImageSize(this.gP - this.nf);
         if (this.Uv != -1L && this.oW != -1L) {
            var36.setEntryPoint(this.Uv + this.oW - this.nf);
            if (this.gO != -1L) {
               throw new RuntimeException("TBI: multiple entry points");
            }
         } else if (this.gO != -1L) {
            var36.setEntryPoint(this.gO - this.nf);
         }

         this.setLoaderInformation(var36.build());
         if (this.io) {
            try {
               String var37 = this.q(this.q.RF);
               if (var37 != null) {
                  IUnit var38 = this.getUnitProcessor().process(var37 + " image", this.getInput(), this, var37, true);
                  if (var38 != null) {
                     this.addChild(var38);
                  }
               }
            } catch (Exception var31) {
               Me.catching(var31);
               this.addNotification(new UnitNotification(NotificationType.UNSUPPORTED_FEATURE, S.L("The machine code was not disassembled")));
            }
         }

         return true;
      }
   }

   private boolean RF() throws IOException {
      try (SeekableByteChannel var1 = this.getInput().getChannel()) {
         for (oM var3 : this.LK) {
            String var4 = Strings.ff("%s", Strings.decodeASCII(var3.q()).trim());
            if (!var4.equals("__objc_classlist") && !var4.equals("__objc_nlclslist")) {
               if (var4.equals("__objc_catlist") || var4.equals("__objc_nlcatlist")) {
                  for (long var14 = var3.oW(); var14 < var3.oW() + var3.zz(); var14 += this.xK ? 8L : 4L) {
                     long var16 = this.xK
                        ? ChannelUtil.getLong(var1, var14, this.Dw.toByteOrder())
                        : ChannelUtil.getInt(var1, var14, this.Dw.toByteOrder()) & 4294967295L;
                     if (!this.q(var1, var16 - this.Uv)) {
                        return false;
                     }
                  }
               }
            } else {
               for (long var5 = var3.oW(); var5 < var3.oW() + var3.zz(); var5 += this.xK ? 8L : 4L) {
                  long var7 = this.xK
                     ? ChannelUtil.getLong(var1, var5, this.Dw.toByteOrder())
                     : ChannelUtil.getInt(var1, var5, this.Dw.toByteOrder()) & 4294967295L;
                  if (!this.q(var1, var7 - this.Uv, false)) {
                     return false;
                  }

                  long var9 = this.xK
                     ? ChannelUtil.getLong(var1, var7 - this.Uv, this.Dw.toByteOrder())
                     : ChannelUtil.getInt(var1, var7 - this.Uv, this.Dw.toByteOrder()) & 4294967295L;
                  if (!this.q(var1, var9 - this.Uv, true)) {
                     return false;
                  }
               }
            }

            if (ckb.q(var3, var4)) {
               long var15 = var3.lm() - this.nf;

               for (long var17 = var15; var17 < var15 + var3.zz(); var17 += this.xK ? 8L : 4L) {
                  this.addSymbol(new SymbolInformation(SymbolType.PTROBJECT, 0, 0L, null, 0L, var17, this.xK ? 8L : 4L));
               }
            }
         }
      }

      return true;
   }

   private boolean q(SeekableByteChannel var1, long var2) throws IOException {
      var1.position(var2);
      ByteBuffer var4 = ByteBuffer.allocate(48).order(this.Dw.toByteOrder());
      if (var1.read(var4) != 48) {
         return false;
      } else {
         var4.rewind();
         ckc var5 = ckc.q(var4);
         if (var5.q == 0L) {
            return false;
         } else {
            return var5.xK != 0L && !this.q(var1, var5.xK - this.Uv, null, false) ? false : var5.Dw == 0L || this.q(var1, var5.Dw - this.Uv, null, false);
         }
      }
   }

   private boolean q(SeekableByteChannel var1, long var2, boolean var4) throws IOException {
      var1.position(var2);
      int var5 = this.xK ? 40 : 20;
      ByteBuffer var6 = ByteBuffer.allocate(var5).order(this.Dw.toByteOrder());
      if (var1.read(var6) != var5) {
         return false;
      } else {
         var6.rewind();
         ckd var7 = ckd.q(var6, this.xK);
         if (var7.Uv == 0L) {
            return true;
         } else {
            long var8 = var7.Uv - this.Uv;
            int var10 = this.xK ? 88 : 80;
            var6 = ByteBuffer.allocate(var10).order(this.Dw.toByteOrder());
            var1.position(var8);
            if (var1.read(var6) != var10) {
               return false;
            } else {
               var6.rewind();
               cke var11 = cke.q(var6, this.xK);
               if (var11.oW == 0L) {
                  return false;
               } else if (var11.oW - this.Uv < 0L) {
                  return false;
               } else {
                  String var12 = this.RF(var11.oW - this.Uv);
                  if (var11.gO != 0L) {
                     long var13 = var11.gO - this.Uv;
                     if (var13 < 0L) {
                        return false;
                     }

                     if (!this.q(var1, var13, var12, var4)) {
                        return false;
                     }
                  }

                  return true;
               }
            }
         }
      }
   }

   private boolean q(SeekableByteChannel var1, long var2, String var4, boolean var5) throws IOException {
      ByteBuffer var6 = ByteBuffer.allocate(8).order(this.Dw.toByteOrder());
      var1.position(var2);
      if (var1.read(var6) != 8) {
         return false;
      } else {
         var6.rewind();
         ckg var7 = ckg.q(var6);
         long var8 = var2 + 8L;
         int var10 = this.xK ? 24 : 12;
         var6 = ByteBuffer.allocate(var10).order(this.Dw.toByteOrder());

         for (int var11 = 0; var11 < var7.RF; var11++) {
            var1.position(var8);
            if (var1.read(var6) != var10) {
               return false;
            }

            var6.rewind();
            ckf var12 = ckf.q(var6, this.xK);
            if (var12.q == 0L) {
               return false;
            }

            StringBuilder var13 = new StringBuilder();
            if (var4 != null) {
               var13.append(var5 ? "+" : "-");
               var13.append("[");
               var13.append(var4);
               var13.append(" ");
               var13.append(this.RF(var12.q - this.Uv));
               var13.append("]");
            }

            this.addSymbol(new SymbolInformation(SymbolType.FUNCTION, 0, 0L, var4 != null ? var13.toString() : null, 0L, var12.xK, 0L));
            var8 += var10;
            var6.rewind();
         }

         return true;
      }
   }

   private boolean q(oM var1, String var2, String var3) {
      if (var3.equals("__DATA")) {
         return true;
      } else {
         int var4 = var1.Dw() & 0xFF;
         switch (var4) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 16:
               return true;
            case 8:
            case 11:
            case 13:
            default:
               if (ckb.RF(var1, var2)) {
                  return true;
               } else {
                  switch (var2) {
                     case "__data":
                     case "__program_vars":
                     case "__dyld":
                     case "__cfstring":
                     case "__rodata":
                     case "__typelink":
                     case "__itablink":
                     case "__gosymtab":
                     case "__gopclntab":
                        return true;
                     default:
                        return false;
                  }
               }
         }
      }
   }

   private boolean q(long var1) {
      ISegmentInformation var3 = null;

      for (ISegmentInformation var5 : this.getValidSections()) {
         if (Longs.compareUnsigned(var1 - this.nf, var5.getOffsetInMemory()) >= 0
            && Longs.compareUnsigned(var1 - this.nf, var5.getOffsetInMemory() + var5.getSizeInMemory()) < 0) {
            var3 = var5;
            break;
         }
      }

      return var3 == null ? true : this.HF.contains(var3);
   }

   private boolean q(CU var1, boolean var2) throws IOException {
      if (var1.io == 0) {
         return true;
      } else {
         try (SeekableByteChannel var3 = this.getInput().getChannel()) {
            ArrayList var4 = new ArrayList();
            var4.addAll(this.zz);
            var4.addAll(this.JY);

            for (oM var6 : var4) {
               int var7 = var6.nf();
               int var8 = var7 * 4;
               long var9 = var6.oW();
               long var11 = var6.lm();

               for (int var13 = this.xK ? 8 : 4; var9 < var6.oW() + var6.zz(); var9 += var13) {
                  int var14 = ChannelUtil.getInt(var3, var1.LK + var8, this.Dw.toByteOrder());
                  if (var14 != Integer.MIN_VALUE && var14 != -1073741824) {
                     if (var14 < 0 || var14 >= this.lm.size()) {
                        throw new RuntimeException("Invalid segment index");
                     }

                     if (var2) {
                        Object[] var10000 = new Object[]{this.lm.get(var14)};
                     }

                     this.q((String)this.lm.get(var14), var11, var13);
                  }

                  var8 += 4;
                  var11 += var13;
               }
            }
         }

         return true;
      }
   }

   private boolean q(eo var1, boolean var2) throws IOException {
      if (var1.zz == 0) {
         return true;
      } else {
         boolean var4;
         try (SeekableByteChannel var3 = this.getInput().getChannel()) {
            if (var2) {
               Object[] var10000 = new Object[0];
            }

            var4 = this.q(var3, var1.lm, var1.lm, "", var2);
         }

         return var4;
      }
   }

   private boolean q(SeekableByteChannel var1, long var2, long var4, String var6, boolean var7) throws IOException {
      if (var7) {
         Object[] var10000 = new Object[]{var6, var4};
      }

      int var8 = ChannelUtil.get(var1, var4) & 255;
      int var9 = 1;
      if (var8 > 127) {
         Object[] var32 = new Object[0];

         try (InputStream var10 = this.getInput().getStream()) {
            var10.skip(var4);
            IntegerLEB128.DecodedInt var11 = IntegerLEB128.decodeULEB128(var10);
            var8 = var11.value;
            var9 = var11.encodedSize;
         }
      }

      if (var8 != 0) {
         byte var26 = ChannelUtil.get(var1, var4 + var9);
         byte var28 = 1;
         if ((var26 & 16) != 0 || (var26 & 8) != 0) {
            throw new RuntimeException(Strings.ff("TBI: export flag %d", var26));
         }

         long var12;
         try (InputStream var14 = this.getInput().getStream()) {
            var14.skip(var4 + var9 + var28);
            var12 = LongLEB128.readULEB128(var14);
         }

         if (var12 == -1L) {
            return false;
         }

         if (var7) {
            Object[] var33 = new Object[]{var6, var12};
         }

         if (this.q(this.Uv + var12)) {
            this.addSymbol(new SymbolInformation(SymbolType.OBJECT, 2, 0L, var6, 0L, this.Uv + var12, 0L));
         } else {
            this.addSymbol(new SymbolInformation(SymbolType.FUNCTION, 2, 0L, var6, 0L, this.Uv + var12, 0L));
         }
      }

      byte var27 = ChannelUtil.get(var1, var4 + var9 + var8);
      if (var27 == 0) {
         if (var7) {
            Object[] var34 = new Object[]{var6};
         }

         return true;
      } else {
         long var29 = var4 + var9 + var8 + 1L;

         for (byte var13 = 0; var13 < var27; var13++) {
            String var31 = this.RF(var29);
            if (var31 == null) {
               return false;
            }

            var29 += Strings.encodeUTF8(var31).length + 1;

            long var15;
            try (InputStream var17 = this.getInput().getStream()) {
               var17.skip(var29);
               LongLEB128.DecodedLong var18 = LongLEB128.decodeULEB128(var17);
               var15 = var18.value;
               var29 += var18.encodedSize;
            }

            if (var15 == 0L) {
               return false;
            }

            if (!this.q(var1, var2, var2 + var15, var6.concat(var31), var7)) {
               return false;
            }
         }

         return true;
      }
   }

   private boolean q(eo var1, boolean var2, boolean var3) throws IOException {
      if (var2) {
         if (var3) {
            Object[] var10000 = new Object[0];
         } else {
            Object[] var51 = new Object[0];
         }
      }

      if (var3) {
         if (var1.nf == 0) {
            return true;
         }
      } else if (var1.oW == 0) {
         return true;
      }

      try (SeekableByteChannel var4 = this.getInput().getChannel()) {
         String var5 = null;
         long var9 = 0L;
         long var15 = var3 ? var1.gO : var1.Uv;
         long var17 = var3 ? var1.nf : var1.oW;
         int var19 = this.xK ? 8 : 4;
         long var20 = var15;
         boolean var22 = false;

         while (!var22 && var20 != var15 + var17) {
            byte var23 = ChannelUtil.get(var4, var20);
            byte var24 = (byte)(var23 & -16);
            byte var25 = (byte)(var23 & 15);
            var20++;
            switch (var24) {
               case -128:
                  InputStream var32 = this.getInput().getStream();
                  var32.skip(var20);
                  LongLEB128.DecodedLong var33 = LongLEB128.decodeULEB128(var32);
                  var9 += var33.value;
                  if (var2) {
                     Object[] var64 = new Object[]{var20 - var15 - 1L, var33.value};
                  }

                  var20 += var33.encodedSize;
                  var32.close();
                  break;
               case -112:
                  this.q(var5, var9 - this.nf, var19);
                  var9 += var19;
                  if (var2) {
                     Object[] var63 = new Object[]{var20 - var15 - 1L};
                  }
                  break;
               case -96:
                  this.q(var5, var9 - this.nf, var19);
                  var9 += var19;
                  InputStream var34 = this.getInput().getStream();
                  var34.skip(var20);
                  LongLEB128.DecodedLong var35 = LongLEB128.decodeULEB128(var34);
                  var9 += var35.value;
                  if (var2) {
                     Object[] var62 = new Object[]{var20 - var15 - 1L, var35.value};
                  }

                  var20 += var35.encodedSize;
                  var34.close();
                  break;
               case -80:
                  this.q(var5, var9 - this.nf, var19);
                  long var36 = (long)var19 + var25 * var19;
                  var9 += var36;
                  if (var2) {
                     Object[] var61 = new Object[]{var20 - var15 - 1L, var36};
                  }
                  break;
               case -64:
                  InputStream var38 = this.getInput().getStream();
                  var38.skip(var20);
                  LongLEB128.DecodedLong var39 = LongLEB128.decodeULEB128(var38);
                  long var40 = var39.value;
                  LongLEB128.DecodedLong var42 = LongLEB128.decodeULEB128(var38);
                  long var43 = var42.value;

                  for (int var45 = 0; var45 < var40; var45++) {
                     this.q(var5, var9 - this.nf, var19);
                     var9 += var19 + var43;
                  }

                  if (var2) {
                     Object[] var60 = new Object[]{var20 - var15 - 1L, var40, var43};
                  }

                  var20 += var39.encodedSize + var42.encodedSize;
                  var38.close();
                  break;
               case -48:
                  throw new RuntimeException(Strings.ff("Bind opcode BIND_OPCODE_THREADED will be managed in a future version"));
               case 0:
                  var22 = true;
                  if (var2) {
                     Object[] var59 = new Object[]{var20 - var15 - 1L};
                  }
                  break;
               case 16:
                  long var50 = var25;
                  if (var2) {
                     Object[] var58 = new Object[]{var20 - var15 - 1L, var50};
                  }
                  break;
               case 32:
                  InputStream var26 = this.getInput().getStream();
                  var26.skip(var20);
                  LongLEB128.DecodedLong var27 = LongLEB128.decodeULEB128(var26);
                  long var49 = var27.value;
                  if (var2) {
                     Object[] var57 = new Object[]{var20 - var15 - 1L, var49};
                  }

                  var20 += var27.encodedSize;
                  var26.close();
                  break;
               case 48:
                  long var11 = var25;
                  if (var2) {
                     Object[] var56 = new Object[]{var20 - var15 - 1L, var11};
                  }
                  break;
               case 64:
                  var5 = this.RF(var20);
                  if (var25 != 0 && var25 != 1 && var25 != 8) {
                     throw new RuntimeException(Strings.ff("TBI: symbol flag %d for %s", var25, var5));
                  }

                  if (var2) {
                     Object[] var55 = new Object[]{var20 - var15 - 1L, var25, var5};
                  }

                  var20 += Strings.encodeUTF8(var5).length + 1;
                  break;
               case 80:
                  if (var25 != 1) {
                     throw new RuntimeException(Strings.ff("TBI: symbol type %d", var25));
                  }

                  if (var2) {
                     Object[] var54 = new Object[]{var20 - var15 - 1L, var25};
                  }
                  break;
               case 96:
                  InputStream var28 = this.getInput().getStream();
                  var28.skip(var20);
                  LongLEB128.DecodedLong var29 = LongLEB128.decodeULEB128(var28);
                  long var13 = var29.value;
                  if (var2) {
                     Object[] var53 = new Object[]{var20 - var15 - 1L, var13};
                  }

                  var20 += var29.encodedSize;
                  var28.close();
                  break;
               case 112:
                  if (var25 > this.getSegmentCount() - 1) {
                     throw new RuntimeException("Invalid segment index");
                  }

                  InputStream var30 = this.getInput().getStream();
                  var30.skip(var20);
                  LongLEB128.DecodedLong var31 = LongLEB128.decodeULEB128(var30);
                  var9 = this.getSegment(var25).getOffsetInMemory() + var31.value;
                  if (var2) {
                     Object[] var52 = new Object[]{var20 - var15 - 1L, Integer.valueOf(var25), var31.value};
                  }

                  var20 += var31.encodedSize;
                  var30.close();
                  break;
               default:
                  throw new RuntimeException(Strings.ff("Bad bind opcode %d", var24));
            }
         }
      }

      return true;
   }

   private boolean q(String var1, long var2, int var4) {
      return ckb.q(var1)
         ? this.addSymbol(new SymbolInformation(SymbolType.PTROBJECT, 1, 0L, var1, 0L, var2, var4))
         : this.addSymbol(new SymbolInformation(SymbolType.PTRFUNCTION, 1, 0L, var1, 0L, var2, var4));
   }

   private boolean RF(eo var1, boolean var2) throws IOException {
      if (var2) {
         Object[] var10000 = new Object[0];
      }

      if (var1.za == 0) {
         return true;
      } else {
         try (SeekableByteChannel var3 = this.getInput().getChannel()) {
            String var4 = null;
            long var8 = 0L;
            int var14 = this.xK ? 8 : 4;
            long var15 = var1.gP;
            boolean var17 = false;

            while (!var17 && var15 != var1.gP + var1.za) {
               byte var18 = ChannelUtil.get(var3, var15);
               byte var19 = (byte)(var18 & -16);
               byte var20 = (byte)(var18 & 15);
               var15++;
               switch (var19) {
                  case -128:
                     InputStream var27 = this.getInput().getStream();
                     var27.skip(var15);
                     LongLEB128.DecodedLong var28 = LongLEB128.decodeULEB128(var27);
                     var8 += var28.value;
                     if (var2) {
                        Object[] var42 = new Object[]{var15 - var1.gP - 1L, var28.value};
                     }

                     var15 += var28.encodedSize;
                     var27.close();
                     break;
                  case -112:
                     this.q(var4, var8 - this.nf, var14);
                     var8 += var14;
                     if (var2) {
                        Object[] var41 = new Object[]{var15 - var1.gP - 1L};
                     }
                     break;
                  case -96:
                  case -80:
                  case -64:
                  default:
                     throw new RuntimeException(Strings.ff("Bad lazy bind opcode %d", var19));
                  case 0:
                     if (var2) {
                        Object[] var40 = new Object[]{var15 - var1.gP - 1L};
                     }
                     break;
                  case 16:
                     long var32 = var20;
                     if (var2) {
                        Object[] var39 = new Object[]{var15 - var1.gP - 1L, var32};
                     }
                     break;
                  case 32:
                     InputStream var21 = this.getInput().getStream();
                     var21.skip(var15);
                     LongLEB128.DecodedLong var22 = LongLEB128.decodeULEB128(var21);
                     long var31 = var22.value;
                     if (var2) {
                        Object[] var38 = new Object[]{var15 - var1.gP - 1L, var31};
                     }

                     var15 += var22.encodedSize;
                     var21.close();
                     break;
                  case 48:
                     long var10 = var20;
                     if (var2) {
                        Object[] var37 = new Object[]{var15 - var1.gP - 1L, var10};
                     }
                     break;
                  case 64:
                     var4 = this.RF(var15);
                     if (var20 != 0 && var20 != 1 && var20 != 8) {
                        throw new RuntimeException(Strings.ff("TBI: symbol flag %d for %s", var20, var4));
                     }

                     if (var2) {
                        Object[] var36 = new Object[]{var15 - var1.gP - 1L, var20, var4};
                     }

                     var15 += Strings.encodeUTF8(var4).length + 1;
                     break;
                  case 80:
                     if (var20 != 1) {
                        throw new RuntimeException(Strings.ff("TBI: symbol type %d", var20));
                     }

                     if (var2) {
                        Object[] var35 = new Object[]{var15 - var1.gP - 1L, var20};
                     }
                     break;
                  case 96:
                     InputStream var23 = this.getInput().getStream();
                     var23.skip(var15);
                     LongLEB128.DecodedLong var24 = LongLEB128.decodeULEB128(var23);
                     long var12 = var24.value;
                     if (var2) {
                        Object[] var34 = new Object[]{var15 - var1.gP - 1L, var12};
                     }

                     var15 += var24.encodedSize;
                     var23.close();
                     break;
                  case 112:
                     if (var20 > this.getSegmentCount() - 1) {
                        throw new RuntimeException("Invalid segment index");
                     }

                     InputStream var25 = this.getInput().getStream();
                     var25.skip(var15);
                     LongLEB128.DecodedLong var26 = LongLEB128.decodeULEB128(var25);
                     var8 = this.getSegment(var20).getOffsetInMemory() + var26.value;
                     if (var2) {
                        Object[] var33 = new Object[]{var15 - var1.gP - 1L, Integer.valueOf(var20), var26.value};
                     }

                     var15 += var26.encodedSize;
                     var25.close();
               }
            }
         }

         return true;
      }
   }

   private boolean q(Bu var1, boolean var2) throws IOException {
      if (var1.Dw == 0) {
         return true;
      } else {
         try (SeekableByteChannel var3 = this.getInput().getChannel()) {
            var3.position(var1.xK);
            int var4 = 0;

            while (var4 < var1.Dw) {
               int var5 = this.xK ? 16 : 12;
               ByteBuffer var6 = ByteBuffer.allocate(var5).order(this.Dw.toByteOrder());
               if (var3.read(var6) != var5) {
                  return false;
               }

               var6.rewind();
               int var7;
               int var8;
               byte var9;
               short var10;
               long var11;
               if (!this.xK) {
                  qV var13 = qV.q(var6);
                  var7 = var13.q;
                  var8 = var13.RF;
                  var9 = var13.xK;
                  var10 = var13.Dw;
                  var11 = var13.Uv & 4294967295L;
               } else {
                  vn var20 = vn.q(var6);
                  var7 = var20.q;
                  var8 = var20.RF;
                  var9 = var20.xK;
                  var10 = var20.Dw;
                  var11 = var20.Uv;
               }

               String var21;
               if (var7 == 0) {
                  var21 = "";
               } else {
                  var21 = this.RF((long)(var1.Uv + var7));
               }

               if (var21 == null) {
                  return false;
               }

               this.lm.add(var21);
               if (var2) {
                  Object[] var10000 = new Object[]{var21};
                  var10000 = new Object[]{Byte.valueOf((byte)var8)};
                  var10000 = new Object[]{var9};
                  var10000 = new Object[]{var10};
                  var10000 = new Object[]{var11};
               }

               if ((var8 & 224) != 0) {
                  if (var2) {
                     Object[] var27 = new Object[0];
                  }

                  if (var21.equals("radr://5614542")) {
                     var4++;
                     continue;
                  }

                  switch (var8 & 0xFF) {
                     case 32:
                        if (var2) {
                           Object[] var30 = new Object[0];
                        }
                     case 38:
                        if (var2) {
                           Object[] var31 = new Object[0];
                        }
                     case 100:
                     case 102:
                     case 132:
                        this.addSymbol(new SymbolInformation(SymbolType.FILE, 0, 0L, var21, 0L, 0L, 0L));
                        break;
                     case 36:
                        if (!var21.isEmpty()) {
                           this.addSymbol(new SymbolInformation(SymbolType.OBJECT, 0, 0L, var21, 0L, var11 - this.nf, 0L));
                        }

                        if (var2) {
                           Object[] var29 = new Object[0];
                        }
                     case 46:
                     case 78:
                        break;
                     default:
                        Object[] var28 = new Object[]{Byte.valueOf((byte)var8)};
                  }
               } else {
                  if (var2) {
                     Object[] var32 = new Object[0];
                  }

                  switch (var8 & 14) {
                     case 0:
                        var4++;
                        continue;
                     case 2:
                     case 14:
                        long var15 = var11 - this.nf;
                        SymbolInformation var14;
                        if (this.q(var11)) {
                           var14 = new SymbolInformation(SymbolType.OBJECT, (var8 & 1) != 0 ? 2 : 0, 0L, var21, 0L, var15, 0L);
                        } else {
                           if ((this.q.RF == 12 || this.q.RF == 16777228) && (var10 & 8) != 0 && (var15 & 1L) == 0L) {
                              var15++;
                           }

                           var14 = new SymbolInformation(SymbolType.FUNCTION, (var8 & 1) != 0 ? 2 : 0, 0L, var21, 0L, var15, 0L);
                        }

                        this.addSymbol(var14);
                        break;
                     default:
                        Object[] var33 = new Object[]{Byte.valueOf((byte)var8)};
                  }
               }

               var4++;
            }
         }

         return true;
      }
   }

   private String RF(long var1) {
      try {
         String var13;
         try (SeekableByteChannel var3 = this.getInput().getChannel()) {
            var3.position(var1);
            int var4 = 1;
            ByteBuffer var5 = ByteBuffer.allocate(var4);
            if (var3.read(var5) == -1) {
               return null;
            }

            byte[] var6 = var5.array();

            while (var6[var6.length - 1] != 0) {
               var5 = ByteBuffer.allocate(++var4);
               var3.position(var1);
               if (var3.read(var5) == -1) {
                  return null;
               }

               var6 = var5.array();
            }

            if (var6.length == 1) {
               return "";
            }

            var13 = Strings.decodeUTF8(var6, 0, var4 - 1);
         }

         return var13;
      } catch (IOException var10) {
         Object[] var10000 = new Object[]{var10};
         return null;
      }
   }

   private String q(int var1) {
      switch (var1) {
         case 7:
            return "x86";
         case 8:
            return "mips";
         case 12:
            return "arm";
         case 16777223:
            return "x86_64";
         case 16777228:
            return "arm64";
         default:
            return null;
      }
   }

   private ProcessorType RF(int var1) {
      switch (var1) {
         case 7:
            return ProcessorType.X86;
         case 8:
            return ProcessorType.MIPS;
         case 12:
            return ProcessorType.ARM;
         case 16777223:
            return ProcessorType.X86_64;
         case 16777228:
            return ProcessorType.ARM64;
         default:
            return null;
      }
   }

   private int xK(int var1) {
      int var2 = 0;
      var2 |= (var1 & 1) != 0 ? 2 : 0;
      var2 |= (var1 & 2) != 0 ? 1 : 0;
      return var2 | ((var1 & 4) != 0 ? 4 : 0);
   }

   @Override
   protected boolean shouldAllocateFullImage() {
      return false;
   }

   @Override
   protected boolean applyRelocations(IVirtualMemory var1, long var2, ILinkInfoProvider var4) {
      return false;
   }
}
