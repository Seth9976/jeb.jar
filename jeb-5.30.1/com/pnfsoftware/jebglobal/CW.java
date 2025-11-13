package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractInstructionOperandList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandGeneric;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class CW extends AbstractInstructionOperandList {
   public static final int RF = 4097;
   private static final CW[] q = new CW[0];
   public static final int xK = 65536;
   public static final int Dw = 131072;
   public static final int Uv = 262144;
   public static final int oW = 524288;
   public static final int gO = 1048576;
   @SerId(1)
   CW.eo nf;
   private static Map gP;
   private static Map za;

   public static CW q(int var0, long var1) {
      return q(var0, var1, 65536);
   }

   public static CW q(long var0, int var2) {
      return q(32, var0, var2);
   }

   public static CW q(int var0, CW.CU var1, long var2, int var4) {
      if (var2 < 0L && var1 == CW.CU.xK) {
         var2 = -var2;
      } else {
         var4 |= var1 == CW.CU.xK ? 262144 : 0;
      }

      if (var1 == CW.CU.RF) {
         var4 |= 1048576;
      }

      return q(var0, var2, var4);
   }

   private static CW q(int var0, long var1, int var3) {
      var3 &= 16777215;
      if (var0 <= 32) {
         var0 = 32;
      } else if (var0 <= 64) {
         var0 = 64;
      }

      if (var3 != 65536) {
         return new CW(1, var0, var1, var3);
      } else {
         Map var4;
         if (var0 == 32) {
            if (gP == null) {
               gP = new HashMap();
            }

            var4 = gP;
         } else {
            if (var0 != 64) {
               return new CW(1, var0, var1, var3);
            }

            if (za == null) {
               za = new HashMap();
            }

            var4 = za;
         }

         CW var5 = (CW)var4.get(var1);
         if (var5 == null) {
            var5 = new CW(1, var0, var1, var3);
            var4.put(var1, var5);
         }

         return var5;
      }
   }

   public static CW q(int var0, int var1, double var2) {
      return new HR(var0, var1, var2, "%1.6e");
   }

   protected CW(int var1, long var2, IInstructionOperandGeneric[] var4, int var5) {
      super(7, var1, var2, var5, var4);
   }

   protected CW(int var1, int var2, long var3, int var5) {
      super(var1, var2, var3, var5);
   }

   public boolean Dw() {
      return (this.getFlags() & 262144) != 0;
   }

   @Override
   public String getRegisterName(long var1) {
      throw new IllegalArgumentException(this + " is not a Register Operand");
   }

   @Override
   public String getAlias(long var1) {
      return this.nf == null ? super.formatOperand(null, 0L).toString() : this.nf.q((int)this.value);
   }

   @Override
   protected CharSequence formatOperand(IInstruction var1, long var2) {
      return (CharSequence)(this.type != 1 || (this.value >= 10L || this.value <= -10L) && (this.getFlags() & 524288) == 0
         ? super.formatOperand(var1, var2)
         : Long.toString(this.value));
   }

   @Override
   public String getPrefix(IInstruction var1) {
      StringBuilder var2 = new StringBuilder(super.getPrefix(var1));
      if ((this.getFlags() & 65536) != 0) {
         var2.append('#');
      }

      if (this.Dw()) {
         var2.append("-");
      }

      return var2.toString();
   }

   @Override
   public String getSuffix(IInstruction var1) {
      String var2 = super.getSuffix(var1);
      return (this.getFlags() & 131072) != 0 ? var2 + "!" : var2;
   }

   public boolean Uv() {
      return (this.getFlags() & 131072) != 0;
   }

   public Long q(long var1, int var3, IMachineContext var4) {
      switch (this.getOperandType()) {
         case 0:
            if (this.RF(var3)) {
               return var1 + fp.q(var3);
            }

            return rT.q(var4, this.getOperandValue(), var3);
         case 1:
         case 2:
         case 3:
            if (this.Dw()) {
               return -this.getOperandValue(var1);
            }

            return this.getOperandValue(var1);
         default:
            return null;
      }
   }

   public boolean RF(int var1) {
      if (this.getOperandType() == 7 && this.oW().length == 1) {
         CW var2 = this.oW()[0];
         return var2.RF(var1);
      } else {
         return false;
      }
   }

   public boolean xK(int var1) {
      if (this.getOperandType() == 7 && this.oW().length == 1) {
         CW var2 = this.oW()[0];
         return var2.xK(var1);
      } else {
         return false;
      }
   }

   public boolean Dw(int var1) {
      if (this.getOperandType() == 7 && this.oW().length == 1) {
         CW var2 = this.oW()[0];
         return var2.Dw(var1);
      } else {
         return false;
      }
   }

   public boolean Uv(int var1) {
      return false;
   }

   public CW[] oW() {
      IInstructionOperand[] var1 = super.getOperands();
      return var1.length > 0 ? (CW[])var1 : q;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + (this.nf == null ? 0 : this.nf.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!super.equals(var1)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         CW var2 = (CW)var1;
         return this.nf == var2.nf;
      }
   }

   @Ser
   public static enum CU {
      q,
      RF,
      xK;
   }

   @Ser
   public static enum eo {
      q(
         new String[]{
            "#0",
            "#1",
            "#2",
            "#3",
            "#4",
            "#5",
            "#6",
            "#7",
            "#8",
            "#9",
            "#10",
            "#11",
            "#12",
            "#13",
            "#14",
            "#15",
            "M32_User",
            "M32_FIQ",
            "M32_IRQ",
            "M32_Svc",
            "10100",
            "10101",
            "M32_Monitor",
            "M32_Abort",
            "11000",
            "11001",
            "M32_Hyp",
            "M32_Undef",
            "11100",
            "11101",
            "11110",
            "M32_System"
         }
      ),
      RF(new String[]{"p0", "p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8", "p9", "p10", "p11", "p12", "p13", "p14", "p15"}),
      xK(new String[]{"LE", "BE"}),
      Dw(new String[]{"NONE", "f", "i", "if", "a", "af", "ai", "aif"}),
      Uv(new String[]{"EQ", "NE", "CS", "CC", "MI", "PL", "VS", "VC", "HI", "LS", "GE", "LT", "GT", "LE", "AL", "NV"}),
      oW(new String[]{"LSL", "LSR", "ASR", "ROR", "RRX", "MSL", "UXTB", "UXTH", "UXTW", "UXTX", "SXTB", "SXTH", "SXTW", "SXTX"}),
      gO(new String[]{"#0x0", "OSHLD", "OSHST", "OSH", "#0x4", "NSHLD", "NSHST", "NSH", "#0x8", "ISHLD", "ISHST", "ISH", "#0xc", "LD", "ST", "SY"}),
      nf(new String[]{"#0x0", "#0x1", "#0x2", "#0x3", "#0x4", "#0x5", "#0x6", "#0x7", "#0x8", "#0x9", "#0xa", "#0xb", "#0xc", "#0xd", "#0xe", "SY"}),
      gP(
         new String[]{
            "PLDL1KEEP",
            "PLDL1STRM",
            "PLDL2KEEP",
            "PLDL2STRM",
            "PLDL3KEEP",
            "PLDL3STRM",
            "#6",
            "#7",
            "PLIL1KEEP",
            "PLIL1STRM",
            "PLIL2KEEP",
            "PLIL2STRM",
            "PLIL3KEEP",
            "PLIL3STRM",
            "#14",
            "#15",
            "PSTL1KEEP",
            "PSTL1STRM",
            "PSTL2KEEP",
            "PSTL2STRM",
            "PSTL3KEEP",
            "PSTL3STRM",
            "#22",
            "#23",
            "#24",
            "#25",
            "#26",
            "#27",
            "#28",
            "#29",
            "#30",
            "#31"
         }
      ),
      za(
         new String[]{
            "S1E1R",
            "S1E1W",
            "S1E0R",
            "S1E0W",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            "S1E2R",
            "S1E2W",
            null,
            null,
            "S12E1R",
            "S12E1W",
            "S12E0R",
            "S12E0W",
            "S1E3R",
            "S1E3W",
            null,
            null,
            null,
            null,
            null,
            null
         }
      ),
      lm(
         new String[]{
            null,
            null,
            null,
            null,
            "IVAC",
            "ISW",
            null,
            null,
            null,
            "CSW",
            null,
            null,
            null,
            "CISW",
            null,
            null,
            null,
            null,
            null,
            null,
            "ZVA",
            null,
            null,
            null,
            "CVAC",
            null,
            "CVAU",
            null,
            "CIVAC",
            null,
            null,
            null
         }
      ),
      zz(new String[]{"IALLUIS", "IALLU", null, "IVAU"}),
      JY(
         new String[]{
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            "VMALLE1IS",
            "VAE1IS",
            "ASIDE1IS",
            "VAAE1IS",
            null,
            "VALE1IS",
            null,
            "VAALE1IS",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            "VMALLE1",
            "VAE1",
            "ASIDE1",
            "VAAE1",
            null,
            "VALE1",
            null,
            "VAALE1",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            "IPAS2E1IS",
            null,
            null,
            null,
            "IPAS2LE1IS",
            null,
            null,
            "ALLE2IS",
            "VAE2IS",
            null,
            null,
            "ALLE1IS",
            "VALE2IS",
            "VMALLS12E1IS",
            null,
            null,
            "IPAS2E1",
            null,
            null,
            null,
            "IPAS2LE1",
            null,
            null,
            "ALLE2",
            "VAE2",
            null,
            null,
            "ALLE1",
            "VALE2",
            "VMALLS12E1",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            "ALLE3IS",
            "VAE3IS",
            null,
            null,
            null,
            "VALE3IS",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            "ALLE3",
            "VAE3",
            null,
            null,
            null,
            "VALE3",
            null,
            null
         }
      ),
      HF(new String[]{null, "SPSel", "DAIFSet", "DAIFClr"}),
      LK(new String[]{""});

      private final String[] io;

      private eo(String[] var3) {
         this.io = var3;
      }

      public String q(int var1) {
         return this.io[var1];
      }

      public String[] q() {
         return this.io;
      }
   }
}
