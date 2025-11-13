package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.IELFSectionEntry;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.Dwarf;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.IDIE;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.IDIEAttribute;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.IDwCompileUnit;
import com.pnfsoftware.jeb.util.format.Charsets;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ArraySeekableByteChannel;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.InflaterInputStream;

public class ciy {
   private static final ILogger lm = GlobalLog.getLogger(ciy.class);
   static final int q = 1;
   static final int RF = 2;
   static final int xK = 3;
   static final int Dw = 4;
   static final int Uv = 5;
   static final int oW = 6;
   static final int gO = 128;
   static final int nf = 255;
   private cjb zz;
   private IELFSectionEntry JY;
   private IELFSectionEntry HF;
   private IELFSectionEntry LK;
   private boolean io = false;
   int gP = -1;
   int za = -1;
   private Map qa = new HashMap();

   private ciy(cjb var1) {
      this.zz = var1;
   }

   private cjc q(IELFSectionEntry var1) throws MemoryException {
      if (var1 == null) {
         return null;
      } else if (var1.getName().startsWith(".z")) {
         if (var1.getSize() < 12L) {
            throw new MemoryException("Invalid compressed section header for " + var1.getName());
         } else {
            int var2 = this.zz.readBEInt(var1.getOffset());
            if (var2 != 1514948930) {
               throw new MemoryException("Invalid compressed section header for " + var1.getName());
            } else {
               byte var3 = 12;
               ByteBuffer var4 = ByteBuffer.allocate((int)var1.getSize() - var3);

               try {
                  this.zz.q(var1.getOffset() + var3, var4);
                  var4.rewind();
                  ByteArrayInputStream var5 = new ByteArrayInputStream(var4.array());
                  InflaterInputStream var6 = new InflaterInputStream(var5);
                  ByteArrayOutputStream var7 = new ByteArrayOutputStream();
                  byte[] var8 = new byte[2048];

                  int var9;
                  while ((var9 = var6.read(var8)) != -1) {
                     var7.write(var8, 0, var9);
                  }

                  byte[] var10 = var7.toByteArray();
                  ArraySeekableByteChannel var11 = new ArraySeekableByteChannel(var10);
                  cjb var12 = new cjb(this.zz.getSpaceBits(), this.zz.getStandardEndianess(), var11);
                  return new cjd(var12, 0L, var10.length);
               } catch (IOException var13) {
                  throw new MemoryException("Can not read memory", var13);
               }
            }
         }
      } else {
         return new cjd(this.zz, var1.getOffset(), (int)var1.getSize());
      }
   }

   List q() throws MemoryException, UnsupportedOperationException {
      cjc var1 = this.q(this.JY);
      ArrayList var2 = new ArrayList();

      while (var1.zz() > 0) {
         if (var1.zz() < 11) {
            byte var5 = var1.Dw();
            if (var5 != 0) {
               byte[] var4 = new byte[1 + var1.zz()];
               var4[0] = var5;
               var1.JY().read(var1.za(), var1.zz(), var4, 1, true);
               lm.warn("Extra non-zero padding in DWARF %s", Formatter.byteArrayToHexString(var4));
               break;
            }
         } else {
            IDwCompileUnit var3 = this.q(var1);
            if (var3 != null) {
               var2.add(var3);
            }
         }
      }

      return var2;
   }

   IDwCompileUnit q(cjc var1) throws MemoryException, UnsupportedOperationException {
      long var2 = var1.lm();
      long var4 = var1.xK();
      if (var4 == -1L) {
         this.gP = 8;
         var1.gO();
      } else {
         this.gP = 4;
      }

      int var6 = var1.RF();
      if (var6 == 1) {
         throw new UnsupportedOperationException("DWARF version 1 is not supported");
      } else {
         long var7;
         if (var6 <= 4) {
            var7 = this.q(var1, this.HF);
            this.za = var1.q();
         } else {
            if (var6 != 5) {
               throw new UnsupportedOperationException("DWARF version " + var6 + " is not managed");
            }

            int var9 = var1.q();
            switch (var9) {
               case 1:
               case 3:
                  this.za = var1.q();
                  var7 = this.q(var1, this.HF);
                  break;
               case 2:
               case 6:
                  this.za = var1.q();
                  var7 = this.q(var1, this.HF);
                  var1.gO();
                  this.q(var1, this.HF);
                  break;
               case 4:
               case 5:
                  this.za = var1.q();
                  var7 = this.q(var1, this.HF);
                  var1.gO();
                  break;
               default:
                  lm.error("Can not process Compilation Unit %xh", var9);
                  return null;
            }
         }

         cit var12 = (cit)this.qa.get(var7);
         if (var12 == null) {
            cjc var10 = this.q(this.HF);
            var10.q(var7);
            var12 = cit.q(var10);
            this.qa.put(var7, var12);
         }

         cjc var13 = this.q(this.LK);
         cja var11 = new cja(var2);
         this.q(var11, var2, var1, var12, var13, var6);
         return var11;
      }
   }

   private void q(cja var1, long var2, cjc var4, cit var5, cjc var6, int var7) throws MemoryException {
      Object var8 = var1;

      while (var4.zz() > 0) {
         long var9 = var4.lm() - var2;
         long var11 = var4.gP();
         if (var11 == 0L && var8 != null) {
            var8 = ((ciu)var8).RF();
            if (var8 == var1) {
               break;
            }
         } else {
            cit.eo var13 = var5.q(var11);
            if (var13 == null) {
               MemoryException var18 = new MemoryException(Strings.ff("no DWARF abbrev_code match in .debug_info @%xh for code %x", var9, var11));
               lm.catchingSilent(var18);
               throw var18;
            }

            ciu var14 = new ciu(var9, var13.RF(), (ciu)var8);

            for (cit.CU var16 : var13.Dw()) {
               civ var17 = this.q(var16, var4, var6, var7, var14);
               var14.q(var17);
            }

            ((ciu)var8).q(var14);
            if (var13.q()) {
               var8 = var14;
            }

            if (var8 == var1) {
               break;
            }
         }
      }
   }

   private civ q(cit.CU var1, cjc var2, cjc var3, int var4, ciu var5) throws MemoryException {
      String var6 = Long.toHexString(var1.q());
      Long.toHexString(var1.RF());
      Dwarf.DwarfAttribute var8 = Dwarf.DwarfAttribute.getByValue((int)var1.q());
      if (var8 != null) {
         var6 = var8.name();
      }

      Long var9 = null;
      Object var10 = null;
      long var11 = var1.RF();
      boolean var13 = false;

      Dwarf.DwarfForm var14;
      do {
         var14 = Dwarf.DwarfForm.getByValue((int)var11);
         String var7 = var14 == null ? "" : var14.name();
         switch (var14) {
            case DW_FORM_addr:
               var9 = this.RF(var2);
               break;
            case DW_FORM_addrx1:
            case DW_FORM_addrx2:
            case DW_FORM_addrx3:
            case DW_FORM_addrx4:
            case DW_FORM_addrx:
               var9 = q(var2, var7);
               break;
            case DW_FORM_block1:
            case DW_FORM_block2:
            case DW_FORM_block4:
            case DW_FORM_block:
            case DW_FORM_exprloc:
               var9 = q(var2, var7);
               var10 = var2.xK(var9.intValue());
               break;
            case DW_FORM_data1:
            case DW_FORM_data2:
            case DW_FORM_data4:
            case DW_FORM_data8:
            case DW_FORM_udata:
               var9 = q(var2, var7);
               break;
            case DW_FORM_data16:
               var10 = this.RF(var2, var7);
               break;
            case DW_FORM_sdata:
               var9 = var2.nf();
               break;
            case DW_FORM_flag:
               var9 = (long)var2.Dw();
            case DW_FORM_flag_present:
               break;
            case DW_FORM_sec_offset:
               var9 = this.q(var2, null);
               break;
            case DW_FORM_rnglistx:
               var9 = var2.gP();
               break;
            case DW_FORM_loclistx:
               var9 = var2.gP();
               break;
            case DW_FORM_ref1:
            case DW_FORM_ref2:
            case DW_FORM_ref4:
            case DW_FORM_ref8:
            case DW_FORM_ref_udata:
               var9 = q(var2, var7);
               break;
            case DW_FORM_ref_addr:
               var9 = var4 == 2 ? this.RF(var2) : this.q(var2, null);
               break;
            case DW_FORM_ref_sig8:
               var9 = var2.gO();
               break;
            case DW_FORM_ref_sup4:
            case DW_FORM_ref_sup8:
               var9 = q(var2, var7);
               break;
            case DW_FORM_string:
               var10 = this.q(var2, true);
               break;
            case DW_FORM_strp:
               var9 = this.q(var2, this.LK);
               var3.q(var9);
               var10 = this.q(var3, false);
               break;
            case DW_FORM_line_strp:
            case DW_FORM_strp_sup:
               var9 = this.q(var2, null);
               break;
            case DW_FORM_strx1:
            case DW_FORM_strx2:
            case DW_FORM_strx3:
            case DW_FORM_strx4:
            case DW_FORM_strx:
               var9 = q(var2, var7);
               break;
            case DW_FORM_implicit_const:
               var9 = var1.xK();
               break;
            case DW_FORM_indirect:
               var11 = var2.gP();
               var13 = true;
               break;
            case DW_FORM_GNU_addr_index:
            case DW_FORM_GNU_str_index:
               var9 = var2.gP();
               break;
            case DW_FORM_GNU_ref_alt:
            case DW_FORM_GNU_strp_alt:
               var9 = this.q(var2, null);
               break;
            default:
               throw new UnsupportedOperationException("Unsupported DW_FORM " + var1.RF());
         }
      } while (var13);

      return new civ(var6, var9, var10, var14.getType() != null ? var14.getType().name() : null, var5);
   }

   public static long q(cjc var0, String var1) throws MemoryException {
      long var2;
      if (var1.endsWith("1")) {
         var2 = var0.q();
      } else if (var1.endsWith("2")) {
         var2 = var0.RF();
      } else if (var1.endsWith("3")) {
         var2 = var0.xK() & 16777215L;
         var0.q(var0.lm() - 1L);
      } else if (var1.endsWith("4")) {
         var2 = var0.xK();
      } else if (var1.endsWith("8")) {
         var2 = var0.gO();
      } else {
         var2 = var0.gP();
      }

      return var2;
   }

   public long RF(cjc var1) throws MemoryException {
      if (this.za <= 0) {
         throw new MemoryException("Uninitialized section offset");
      } else {
         return var1.RF(this.za);
      }
   }

   public long q(cjc var1, IELFSectionEntry var2) throws MemoryException {
      if (this.gP <= 0) {
         throw new MemoryException("Uninitialized section offset");
      } else {
         long var3 = var1.lm();
         long var5 = var1.RF(this.gP);
         if (this.io && var2 != null && var1.xK(var3)) {
            var5 -= var2.getOffset();
         }

         return var5;
      }
   }

   private BigInteger RF(cjc var1, String var2) throws MemoryException {
      long var3 = var1.gO();
      long var5 = var1.gO();
      if (this.zz.getStandardEndianess() != Endianness.LITTLE_ENDIAN) {
         long var7 = var3;
         var3 = var5;
         var5 = var7;
      }

      BigInteger var9 = BigInteger.valueOf(var5);
      var9 = var9.shiftLeft(64);
      return var9.or(BigInteger.valueOf(var3));
   }

   private String q(cjc var1, boolean var2) throws MemoryException {
      ByteArrayOutputStream var3 = new ByteArrayOutputStream();

      while (var1.zz() > 0) {
         byte var4 = var1.Dw();
         if (var4 == 0) {
            break;
         }

         var3.write(var4);
      }

      return new String(var3.toByteArray(), Charsets.findCharset("UTF-8"));
   }

   public static List q(IELFUnit var0) throws UnsupportedOperationException {
      new ArrayList();
      cjb var2 = null;

      List var1;
      try {
         var2 = new cjb(var0.getLoaderInformation().getWordSize(), var0.getLoaderInformation().getEndianness(), var0.getInput());
         ciy var3 = new ciy(var2);

         for (IELFSectionEntry var5 : var0.getSectionEntries()) {
            if (var5.getName().equals(".debug_abbrev")) {
               var3.HF = var5;
            } else if (var5.getName().equals(".debug_info")) {
               var3.JY = var5;
            } else if (var5.getName().equals(".debug_str")) {
               var3.LK = var5;
            } else if (var5.getName().equals(".zdebug_abbrev")) {
               var3.HF = var5;
            } else if (var5.getName().equals(".zdebug_info")) {
               var3.JY = var5;
            } else if (var5.getName().equals(".zdebug_str")) {
               var3.LK = var5;
            } else if (var5.getName().equals(".rela.debug_info") || var5.getName().equals(".rel.debug_info")) {
               com.pnfsoftware.jeb.corei.parsers.elf.KZ var6 = new com.pnfsoftware.jeb.corei.parsers.elf.KZ(var0, var2, 0L);
               com.pnfsoftware.jeb.corei.parsers.elf.zJ var7 = var6.q();
               if (var7 != null) {
                  var7.q(var2x -> var5 == var0.getSectionEntry(var2x.getSectionIndex()));
               }

               var3.io = true;
            }
         }

         if (var3.JY == null || var3.HF == null) {
            return null;
         }

         var1 = var3.q();
      } catch (IOException var16) {
         lm.catching(var16);
         return null;
      } finally {
         if (var2 != null) {
            try {
               var2.q();
            } catch (IOException var15) {
            }
         }
      }

      return var1;
   }

   public void q(IDIE var1) {
      this.q(0, var1);
   }

   private void q(int var1, IDIE var2) {
      StringBuilder var3 = new StringBuilder();
      var3.append("<").append(var1).append(", ").append(Long.toHexString(var2.getOffset())).append("h>  ").append(Strings.pad(' ', 2 * var1));
      int var4 = var3.length();
      var3.append(var2.getTagName());
      Object[] var10000 = new Object[0];

      for (IDIEAttribute var6 : var2.getAttributes()) {
         var10000 = new Object[]{
            Strings.pad(' ', var4),
            var6.getName(),
            var6.getRawForm() == null ? "" : Long.toHexString(var6.getRawForm()) + "h",
            var6.getForm() == null ? "" : " -> " + var6.getForm()
         };
      }

      for (IDIE var8 : var2.getChildren()) {
         this.q(var1 + 1, var8);
      }
   }
}
