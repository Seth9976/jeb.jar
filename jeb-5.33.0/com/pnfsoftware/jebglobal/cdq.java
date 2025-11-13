package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.IELFSectionEntry;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.Dwarf;
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

public class cdq {
   private static final ILogger kS = GlobalLog.getLogger(cdq.class);
   private cdt wS;
   private IELFSectionEntry UT;
   private IELFSectionEntry E;
   private IELFSectionEntry sY;
   private boolean ys = false;
   int pC = -1;
   int A = -1;
   private Map ld = new HashMap();

   private cdq(cdt var1) {
      this.wS = var1;
   }

   private cdu pC(IELFSectionEntry var1) throws MemoryException {
      if (var1 == null) {
         return null;
      } else if (var1.getName().startsWith(".z")) {
         if (var1.getSize() < 12L) {
            throw new MemoryException("Invalid compressed section header for " + var1.getName());
         } else {
            int var2 = this.wS.readBEInt(var1.getOffset());
            if (var2 != 1514948930) {
               throw new MemoryException("Invalid compressed section header for " + var1.getName());
            } else {
               byte var3 = 12;
               ByteBuffer var4 = ByteBuffer.allocate((int)var1.getSize() - var3);

               try {
                  this.wS.pC(var1.getOffset() + var3, var4);
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
                  cdt var12 = new cdt(this.wS.getSpaceBits(), this.wS.getStandardEndianess(), var11);
                  return new cdv(var12, 0L, var10.length);
               } catch (IOException var13) {
                  throw new MemoryException("Can not read memory", var13);
               }
            }
         }
      } else {
         return new cdv(this.wS, var1.getOffset(), (int)var1.getSize());
      }
   }

   List pC() throws MemoryException, UnsupportedOperationException {
      cdu var1 = this.pC(this.UT);
      ArrayList var2 = new ArrayList();

      while (var1.gp() > 0) {
         if (var1.gp() < 11) {
            byte var5 = var1.wS();
            if (var5 != 0) {
               byte[] var4 = new byte[1 + var1.gp()];
               var4[0] = var5;
               var1.oT().read(var1.ys(), var1.gp(), var4, 1, true);
               kS.warn("Extra non-zero padding in DWARF %s", Formatter.byteArrayToHexString(var4));
               break;
            }
         } else {
            IDwCompileUnit var3 = this.pC(var1);
            if (var3 != null) {
               var2.add(var3);
            }
         }
      }

      return var2;
   }

   IDwCompileUnit pC(cdu var1) throws MemoryException, UnsupportedOperationException {
      long var2 = var1.ld();
      long var4 = var1.kS();
      if (var4 == -1L) {
         this.pC = 8;
         var1.UT();
      } else {
         this.pC = 4;
      }

      int var6 = var1.A();
      if (var6 == 1) {
         throw new UnsupportedOperationException("DWARF version 1 is not supported");
      } else {
         long var7;
         if (var6 <= 4) {
            var7 = this.pC(var1, this.E);
            this.A = var1.pC();
         } else {
            if (var6 != 5) {
               throw new UnsupportedOperationException("DWARF version " + var6 + " is not managed");
            }

            int var9 = var1.pC();
            switch (var9) {
               case 1:
               case 3:
                  this.A = var1.pC();
                  var7 = this.pC(var1, this.E);
                  break;
               case 2:
               case 6:
                  this.A = var1.pC();
                  var7 = this.pC(var1, this.E);
                  var1.UT();
                  this.pC(var1, this.E);
                  break;
               case 4:
               case 5:
                  this.A = var1.pC();
                  var7 = this.pC(var1, this.E);
                  var1.UT();
                  break;
               default:
                  kS.error("Can not process Compilation Unit %xh", var9);
                  return null;
            }
         }

         cdl var12 = (cdl)this.ld.get(var7);
         if (var12 == null) {
            cdu var10 = this.pC(this.E);
            var10.pC(var7);
            var12 = cdl.pC(var10);
            this.ld.put(var7, var12);
         }

         cdu var13 = this.pC(this.sY);
         cds var11 = new cds(var2);
         this.pC(var11, var2, var1, var12, var13, var6);
         return var11;
      }
   }

   private void pC(cds var1, long var2, cdu var4, cdl var5, cdu var6, int var7) throws MemoryException {
      Object var8 = var1;

      while (var4.gp() > 0) {
         long var9 = var4.ld() - var2;
         long var11 = var4.sY();
         if (var11 == 0L && var8 != null) {
            var8 = ((cdm)var8).A();
            if (var8 == var1) {
               break;
            }
         } else {
            cdl.Av var13 = var5.pC(var11);
            if (var13 == null) {
               MemoryException var18 = new MemoryException(Strings.ff("no DWARF abbrev_code match in .debug_info @%xh for code %x", var9, var11));
               kS.catchingSilent(var18);
               throw var18;
            }

            cdm var14 = new cdm(var9, var13.A(), (cdm)var8);

            for (cdl.Sv var16 : var13.kS()) {
               cdn var17 = this.pC(var16, var4, var6, var7, var14);
               var14.pC(var17);
            }

            ((cdm)var8).pC(var14);
            if (var13.pC()) {
               var8 = var14;
            }

            if (var8 == var1) {
               break;
            }
         }
      }
   }

   private cdn pC(cdl.Sv var1, cdu var2, cdu var3, int var4, cdm var5) throws MemoryException {
      String var6 = Long.toHexString(var1.pC());
      Long.toHexString(var1.A());
      Dwarf.DwarfAttribute var8 = Dwarf.DwarfAttribute.getByValue((int)var1.pC());
      if (var8 != null) {
         var6 = var8.name();
      }

      Long var9 = null;
      Object var10 = null;
      long var11 = var1.A();
      boolean var13 = false;

      Dwarf.DwarfForm var14;
      do {
         var14 = Dwarf.DwarfForm.getByValue((int)var11);
         String var7 = var14 == null ? "" : var14.name();
         switch (var14) {
            case DW_FORM_addr:
               var9 = this.A(var2);
               break;
            case DW_FORM_addrx1:
            case DW_FORM_addrx2:
            case DW_FORM_addrx3:
            case DW_FORM_addrx4:
            case DW_FORM_addrx:
               var9 = pC(var2, var7);
               break;
            case DW_FORM_block1:
            case DW_FORM_block2:
            case DW_FORM_block4:
            case DW_FORM_block:
            case DW_FORM_exprloc:
               var9 = pC(var2, var7);
               var10 = var2.A(var9.intValue());
               break;
            case DW_FORM_data1:
            case DW_FORM_data2:
            case DW_FORM_data4:
            case DW_FORM_data8:
            case DW_FORM_udata:
               var9 = pC(var2, var7);
               break;
            case DW_FORM_data16:
               var10 = this.A(var2, var7);
               break;
            case DW_FORM_sdata:
               var9 = var2.E();
               break;
            case DW_FORM_flag:
               var9 = (long)var2.wS();
            case DW_FORM_flag_present:
               break;
            case DW_FORM_sec_offset:
               var9 = this.pC(var2, null);
               break;
            case DW_FORM_rnglistx:
               var9 = var2.sY();
               break;
            case DW_FORM_loclistx:
               var9 = var2.sY();
               break;
            case DW_FORM_ref1:
            case DW_FORM_ref2:
            case DW_FORM_ref4:
            case DW_FORM_ref8:
            case DW_FORM_ref_udata:
               var9 = pC(var2, var7);
               break;
            case DW_FORM_ref_addr:
               var9 = var4 == 2 ? this.A(var2) : this.pC(var2, null);
               break;
            case DW_FORM_ref_sig8:
               var9 = var2.UT();
               break;
            case DW_FORM_ref_sup4:
            case DW_FORM_ref_sup8:
               var9 = pC(var2, var7);
               break;
            case DW_FORM_string:
               var10 = this.pC(var2, true);
               break;
            case DW_FORM_strp:
               var9 = this.pC(var2, this.sY);
               var3.pC(var9);
               var10 = this.pC(var3, false);
               break;
            case DW_FORM_line_strp:
            case DW_FORM_strp_sup:
               var9 = this.pC(var2, null);
               break;
            case DW_FORM_strx1:
            case DW_FORM_strx2:
            case DW_FORM_strx3:
            case DW_FORM_strx4:
            case DW_FORM_strx:
               var9 = pC(var2, var7);
               break;
            case DW_FORM_implicit_const:
               var9 = var1.kS();
               break;
            case DW_FORM_indirect:
               var11 = var2.sY();
               var13 = true;
               break;
            case DW_FORM_GNU_addr_index:
            case DW_FORM_GNU_str_index:
               var9 = var2.sY();
               break;
            case DW_FORM_GNU_ref_alt:
            case DW_FORM_GNU_strp_alt:
               var9 = this.pC(var2, null);
               break;
            default:
               throw new UnsupportedOperationException("Unsupported DW_FORM " + var1.A());
         }
      } while (var13);

      return new cdn(var6, var9, var10, var14.getType() != null ? var14.getType().name() : null, var5);
   }

   public static long pC(cdu var0, String var1) throws MemoryException {
      long var2;
      if (var1.endsWith("1")) {
         var2 = var0.pC();
      } else if (var1.endsWith("2")) {
         var2 = var0.A();
      } else if (var1.endsWith("3")) {
         var2 = var0.kS() & 16777215L;
         var0.pC(var0.ld() - 1L);
      } else if (var1.endsWith("4")) {
         var2 = var0.kS();
      } else if (var1.endsWith("8")) {
         var2 = var0.UT();
      } else {
         var2 = var0.sY();
      }

      return var2;
   }

   public long A(cdu var1) throws MemoryException {
      if (this.A <= 0) {
         throw new MemoryException("Uninitialized section offset");
      } else {
         return var1.pC(this.A);
      }
   }

   public long pC(cdu var1, IELFSectionEntry var2) throws MemoryException {
      if (this.pC <= 0) {
         throw new MemoryException("Uninitialized section offset");
      } else {
         long var3 = var1.ld();
         long var5 = var1.pC(this.pC);
         if (this.ys && var2 != null && var1.A(var3)) {
            var5 -= var2.getOffset();
         }

         return var5;
      }
   }

   private BigInteger A(cdu var1, String var2) throws MemoryException {
      long var3 = var1.UT();
      long var5 = var1.UT();
      if (this.wS.getStandardEndianess() != Endianness.LITTLE_ENDIAN) {
         long var7 = var3;
         var3 = var5;
         var5 = var7;
      }

      BigInteger var9 = BigInteger.valueOf(var5);
      var9 = var9.shiftLeft(64);
      return var9.or(BigInteger.valueOf(var3));
   }

   private String pC(cdu var1, boolean var2) throws MemoryException {
      ByteArrayOutputStream var3 = new ByteArrayOutputStream();

      while (var1.gp() > 0) {
         byte var4 = var1.wS();
         if (var4 == 0) {
            break;
         }

         var3.write(var4);
      }

      return new String(var3.toByteArray(), Charsets.findCharset("UTF-8"));
   }

   public static List pC(IELFUnit var0) throws UnsupportedOperationException {
      new ArrayList();
      cdt var2 = null;

      List var1;
      try {
         var2 = new cdt(var0.getLoaderInformation().getWordSize(), var0.getLoaderInformation().getEndianness(), var0.getInput());
         cdq var3 = new cdq(var2);

         for (IELFSectionEntry var5 : var0.getSectionEntries()) {
            if (var5.getName().equals(".debug_abbrev")) {
               var3.E = var5;
            } else if (var5.getName().equals(".debug_info")) {
               var3.UT = var5;
            } else if (var5.getName().equals(".debug_str")) {
               var3.sY = var5;
            } else if (var5.getName().equals(".zdebug_abbrev")) {
               var3.E = var5;
            } else if (var5.getName().equals(".zdebug_info")) {
               var3.UT = var5;
            } else if (var5.getName().equals(".zdebug_str")) {
               var3.sY = var5;
            } else if (var5.getName().equals(".rela.debug_info") || var5.getName().equals(".rel.debug_info")) {
               com.pnfsoftware.jeb.corei.parsers.elf.io var6 = new com.pnfsoftware.jeb.corei.parsers.elf.io(var0, var2, 0L);
               com.pnfsoftware.jeb.corei.parsers.elf.Pj var7 = var6.pC();
               if (var7 != null) {
                  var7.pC(var2x -> var5 == var0.getSectionEntry(var2x.getSectionIndex()));
               }

               var3.ys = true;
            }
         }

         if (var3.UT == null || var3.E == null) {
            return null;
         }

         var1 = var3.pC();
      } catch (IOException var16) {
         kS.catching(var16);
         return null;
      } finally {
         if (var2 != null) {
            try {
               var2.pC();
            } catch (IOException var15) {
            }
         }
      }

      return var1;
   }
}
