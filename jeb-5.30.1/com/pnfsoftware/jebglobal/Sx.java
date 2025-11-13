package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

class Sx implements Mt {
   private static final String gP = "documentation/dui0801/l/";
   Predicate q;
   private final String za;
   private final String[] lm;
   private final Map zz;
   private final String[] JY;
   private final Map HF;
   private final String[] LK;
   private final String io;
   static final Sx RF = new tM(
      "documentation/dui0801/l/A64-General-Instructions/",
      "--A64-",
      new String[]{"", "--immediate-", "--register-"},
      new String[]{
         "AUTDA--AUTDZA",
         "AUTDB--AUTDZB",
         "AUTIA--AUTIZA--AUTIA1716--AUTIASP--AUTIAZ",
         "AUTIB--AUTIZB--AUTIB1716--AUTIBSP--AUTIBZ",
         "BLRAA--BLRAAZ--BLRAB--BLRABZ",
         "BRAA--BRAAZ--BRAB--BRABZ",
         "PACDA--PACDZA",
         "PACDB--PACDZB",
         "PACIA--PACIZA--PACIA1716--PACIASP--PACIAZ",
         "PACIB--PACIZB--PACIB1716--PACIBSP--PACIBZ",
         "XPACD--XPACI--XPACLRI",
         "CRC32B--CRC32H--CRC32W--CRC32X",
         "CRC32CB--CRC32CH--CRC32CW--CRC32CX",
         "ERETAA--ERETAB",
         "RETAA--RETAB"
      },
      null,
      Maps.toMap("--shifted-register-", Arrays.asList("BIC", "BICS", "EON", "NEG", "ORN"))
   );
   static final Sx xK = new jY(
         "documentation/dui0801/l/A64-Data-Transfer-Instructions/",
         "--A64-",
         new String[]{"", "--immediate-"},
         new String[]{
            "CASA--CASAL--CAS--CASL--CASAL--CAS--CASL",
            "CASAB--CASALB--CASB--CASLB",
            "CASAH--CASALH--CASH--CASLH",
            "CASPA--CASPAL--CASP--CASPL--CASPAL--CASP--CASPL",
            "LDADDA--LDADDAL--LDADD--LDADDL--LDADDAL--LDADD--LDADDL",
            "LDADDAB--LDADDALB--LDADDB--LDADDLB",
            "LDADDAH--LDADDALH--LDADDH--LDADDLH",
            "LDCLRA--LDCLRAL--LDCLR--LDCLRL--LDCLRAL--LDCLR--LDCLRL",
            "LDCLRAB--LDCLRALB--LDCLRB--LDCLRLB",
            "LDCLRAH--LDCLRALH--LDCLRH--LDCLRLH",
            "LDEORA--LDEORAL--LDEOR--LDEORL--LDEORAL--LDEOR--LDEORL",
            "LDEORAB--LDEORALB--LDEORB--LDEORLB",
            "LDEORAH--LDEORALH--LDEORH--LDEORLH",
            "LDRAA--LDRAB--LDRAB",
            "LDSETA--LDSETAL--LDSET--LDSETL--LDSETAL--LDSET--LDSETL",
            "LDSETAB--LDSETALB--LDSETB--LDSETLB",
            "LDSETAH--LDSETALH--LDSETH--LDSETLH",
            "LDSMAXA--LDSMAXAL--LDSMAX--LDSMAXL--LDSMAXAL--LDSMAX--LDSMAXL",
            "LDSMAXAB--LDSMAXALB--LDSMAXB--LDSMAXLB",
            "LDSMAXAH--LDSMAXALH--LDSMAXH--LDSMAXLH",
            "LDSMINA--LDSMINAL--LDSMIN--LDSMINL--LDSMINAL--LDSMIN--LDSMINL",
            "LDSMINAB--LDSMINALB--LDSMINB--LDSMINLB",
            "LDSMINAH--LDSMINALH--LDSMINH--LDSMINLH",
            "LDUMAXA--LDUMAXAL--LDUMAX--LDUMAXL--LDUMAXAL--LDUMAX--LDUMAXL",
            "LDUMAXAB--LDUMAXALB--LDUMAXB--LDUMAXLB",
            "LDUMAXAH--LDUMAXALH--LDUMAXH--LDUMAXLH",
            "LDUMINA--LDUMINAL--LDUMIN--LDUMINL--LDUMINAL--LDUMIN--LDUMINL",
            "LDUMINAB--LDUMINALB--LDUMINB--LDUMINLB",
            "LDUMINAH--LDUMINALH--LDUMINH--LDUMINLH",
            "STADD--STADDL--STADDL",
            "STADDB--STADDLB",
            "STADDH--STADDLH",
            "STCLR--STCLRL--STCLRL",
            "STCLRB--STCLRLB",
            "STCLRH--STCLRLH",
            "STEOR--STEORL--STEORL",
            "STEORB--STEORLB",
            "STEORH--STEORLH",
            "STSET--STSETL--STSETL",
            "STSETB--STSETLB",
            "STSETH--STSETLH",
            "STSMAX--STSMAXL--STSMAXL",
            "STSMAXB--STSMAXLB",
            "STSMAXH--STSMAXLH",
            "STSMIN--STSMINL--STSMINL",
            "STSMINB--STSMINLB",
            "STSMINH--STSMINLH",
            "STUMAX--STUMAXL--STUMAXL",
            "STUMAXB--STUMAXLB",
            "STUMAXH--STUMAXLH",
            "STUMIN--STUMINL--STUMINL",
            "STUMINB--STUMINLB",
            "STUMINH--STUMINLH",
            "SWPA--SWPAL--SWP--SWPL--SWPAL--SWP--SWPL",
            "SWPAB--SWPALB--SWPB--SWPLB",
            "SWPAH--SWPALH--SWPH--SWPLH"
         },
         null,
         Maps.toMap("--unscaled-offset-", Arrays.asList("PRFUM"))
      )
      .q(var0 -> Strings.startsWith(var0, "CAS", "LD", "PRF", "ST", "SWP"));
   static final Sx Dw = new ac(
         "documentation/dui0801/l/A64-Floating-point-Instructions/",
         "--A64-",
         new String[]{"", "--scalar-"},
         null,
         null,
         Maps.toMap("--scalar--immediate-", Arrays.asList("FMOV"), "--scalar--integer-", Arrays.asList("FCVTZS", "FCVTZU", "SCVTF", "UCVTF"))
      )
      .q(var0 -> Strings.startsWith(var0, "F", "SCVTF", "UCVTF"));
   static final Sx Uv = new Sx(
         "documentation/dui0801/l/A64-SIMD-Vector-Instructions/",
         "--A64-",
         new String[]{"", "--vector-", "--vector--zero-", "--vector--immediate-", "--vector--register-", "--vector--element-"},
         q(
            new String[]{
               "ADDHN",
               "FCVTL",
               "FCVTN",
               "FCVTXN",
               "PMULL",
               "RADDHN",
               "RSHRN",
               "RSUBHN",
               "SABAL",
               "SABDL",
               "SADDL",
               "SADDW",
               "UABAL",
               "UABDL",
               "UADDL",
               "UADDW",
               "SHLL",
               "SSHLL",
               "USHLL",
               "SSUBL",
               "USUBL",
               "SSUBW",
               "USUBW",
               "SMLAL",
               "SMLSL",
               "SMULL",
               "SQDMLAL",
               "SQDMLSL",
               "SQDMULL",
               "UMLAL",
               "UMLSL",
               "UMULL",
               "SHRN",
               "SQRSHRN",
               "SQRSHRUN",
               "SQSHRN",
               "SQSHRUN",
               "SQXTN",
               "SQXTUN",
               "UQRSHRN",
               "UQSHRN",
               "UQXTN",
               "SXTL",
               "UXTL",
               "XTN"
            }
         ),
         null,
         Maps.toMap("--vector--single-structure-", Arrays.asList("LD1", "LD2", "LD3", "LD4", "ST1", "ST2", "ST3", "ST4"))
      )
      .q(
         var0 -> Strings.startsWith(
               var0,
               "FCA",
               "FCME",
               "FCMG",
               "FCML",
               "FML",
               "ML",
               "SML",
               "UML",
               "FMULX",
               "SMULL2",
               "UMULL2",
               "CME",
               "CMG",
               "CMH",
               "CML",
               "CMT",
               "ADDHN",
               "ADDV",
               "BIF",
               "BIT",
               "BSL",
               "DUP",
               "FABD",
               "FAC",
               "FADDP",
               "FCVTL",
               "FCVTXN",
               "FRECP",
               "FRSQRTE",
               "FRSQRTS",
               "MOVI",
               "MVNI",
               "PM",
               "LD1",
               "LD2",
               "LD3",
               "LD4",
               "ST1",
               "ST2",
               "ST3",
               "ST4",
               "RADDHN",
               "RS",
               "SMOV",
               "UMOV",
               "SA",
               "UA",
               "SH",
               "UH",
               "SL",
               "SSH",
               "USH",
               "SSR",
               "USR",
               "SSU",
               "USU",
               "SR",
               "UR",
               "SQ",
               "UQ",
               "SUQ",
               "USQ",
               "SXTL",
               "UXTL",
               "TRN",
               "URE",
               "URS",
               "UZP",
               "ZIP",
               "XTN"
            )
            || Strings.isContainedIn(
               var0,
               "ADDP",
               "EXT",
               "FCVTN",
               "FCVTN2",
               "TBL",
               "TBX",
               "FMAXNMP",
               "FMAXNMV",
               "FMAXP",
               "FMAXV",
               "FMINNMP",
               "FMINNMV",
               "FMINP",
               "FMINV",
               "SMAXP",
               "SMAXV",
               "SMINP",
               "SMINV",
               "UMAXP",
               "UMAXV",
               "UMINP",
               "UMINV"
            )
      );
   private static String[] qa = new String[]{"EQ", "NE", "CS", "CC", "MI", "PL", "VS", "VC", "HI", "LS", "GE", "LT", "GT", "LE"};
   static final Sx oW = new ZE(
      "documentation/dui0801/l/A32-and-T32-Instructions/",
      "--A32-",
      new String[]{"", "--immediate-offset-"},
      new String[]{
         "BLX--BLXNS",
         "BX--BXNS",
         "CBZ-and-CBNZ",
         "CDP-and-CDP2",
         "CMP-and-CMN",
         "LDC-and-LDC2",
         "MCR-and-MCR2",
         "MCRR-and-MCRR2",
         "MRC-and-MRC2",
         "MRRC-and-MRRC2",
         "PKHBT-and-PKHTB",
         "PLD--PLDW--and-PLI",
         "STC-and-STC2",
         "SWP-and-SWPB",
         "TBB-and-TBH",
         "TT--TTT--TTA--TTAT"
      },
      new String[]{
         "CPS",
         "CRC32C",
         "CRC32",
         "LDAEX",
         "LDM",
         "LDREX",
         "RFE",
         "SRS",
         "SMLSD",
         "SMLSLD",
         "SMMLA",
         "SMMLS",
         "SMMUL",
         "SMUAD",
         "SMUSD",
         "STLEX",
         "STM",
         "STREX"
      },
      Maps.toMap(
         "--PC-relative-",
         Arrays.asList("ADR"),
         "--PSR-to-general-purpose-register-",
         Arrays.asList("MRS"),
         "--general-purpose-register-to-PSR-",
         Arrays.asList("MSR")
      )
   );
   static final Sx gO = new ea(
         "documentation/dui0801/l/Floating-point-Instructions--32-bit-/",
         "--A32-",
         new String[]{"", "--floating-point-"},
         new String[]{"VCMP--VCMPE", "VFMA--VFMS--VFNMA--VFNMS"},
         new String[]{"VSEL"},
         null
      )
      .q(var0 -> Strings.startsWith(var0, "VCMP", "VDIV", "VFN", "VJCVT", "VNM", "VSEL", "VSQ"));
   static final Sx nf = new dt(
         "documentation/dui0801/l/Advanced-SIMD-Instructions--32-bit-/",
         "--A32-",
         new String[]{"", "--register-", "--by-element-", "--by-scalar-", "--by-vector-or-by-scalar-", "--by-signed-variable-", "--by-immediate-"},
         new String[]{
            "FLDMDBX--FLDMIAX",
            "FSTMDBX--FSTMIAX",
            "VABA-and-VABAL",
            "VABD-and-VABDL",
            "VACLE--VACLT--VACGE-and-VACGT",
            "VADDL-and-VADDW",
            "VFMA--VFMS",
            "VMAX-and-VMIN",
            "VMAXNM--VMINNM",
            "VPMAX-and-VPMIN",
            "VQDMLAL-and-VQDMLSL",
            "VQMOVN-and-VQMOVUN",
            "VQRSHRN-and-VQRSHRUN",
            "VQSHL-and-VQSHLU",
            "VQSHRN-and-VQSHRUN",
            "VREV16--VREV32--and-VREV64",
            "VSUBL-and-VSUBW",
            "VTBL-and-VTBX"
         },
         new String[]{"VRINT", "VLDM", "VSTM"},
         Maps.toMap("--multiple-n-element-structures-", Arrays.asList("VLDn", "VSTn"))
      )
      .q(var0 -> Strings.startsWith(var0, "V", "F"));

   public Sx(String var1, String var2, String[] var3, String[] var4, String[] var5, Map var6) {
      this.za = var1;
      this.io = var2;
      this.lm = var3;
      this.JY = var4;
      this.LK = var5;
      if (var4 == null) {
         this.HF = null;
      } else {
         this.HF = new HashMap();

         for (int var7 = 0; var7 < var4.length; var7++) {
            String[] var8 = var4[var7].split("-(and)?-");

            for (String var12 : var8) {
               while (var12.startsWith("-") || var12.startsWith("and")) {
                  var12 = var12.startsWith("-") ? var12.substring(1) : var12.substring(3);
               }

               while (var12.endsWith("-") || var12.endsWith("and")) {
                  var12 = var12.startsWith("-") ? var12.substring(0, var12.length() - 1) : var12.substring(0, var12.length() - 3);
               }

               if (!var12.isEmpty()) {
                  this.HF.put(var12, var7);
               }
            }
         }
      }

      if (var6 == null) {
         this.zz = null;
      } else {
         this.zz = new HashMap();

         for (Entry var14 : var6.entrySet()) {
            for (String var16 : (List)var14.getValue()) {
               this.zz.put(var16, (String)var14.getKey());
            }
         }
      }
   }

   public Sx q(Predicate var1) {
      this.q = var1;
      return this;
   }

   @Override
   public boolean xK(String var1) {
      return this.q == null || this.q.test(var1);
   }

   @Override
   public String q(String var1) {
      return var1;
   }

   public String Dw(String var1) {
      return this.io;
   }

   @Override
   public List RF(String var1) {
      ArrayList var2 = new ArrayList();
      String var3 = this.oW(var1);
      if (var3 != null) {
         var1 = var3;
      } else if (this.LK != null) {
         for (String var7 : this.LK) {
            if (var1.startsWith(var7)) {
               var1 = var7;
               break;
            }
         }
      }

      String var9 = this.Uv(var1);
      if (var9 != null) {
         var2.add(Strings.safe(this.za) + var9 + this.Dw(var1));
      } else {
         for (String var8 : this.lm) {
            var2.add(Strings.safe(this.za) + var1 + var8 + this.Dw(var1));
         }
      }

      return var2;
   }

   private String Uv(String var1) {
      if (this.zz == null) {
         return null;
      } else {
         String var2 = (String)this.zz.get(var1);
         return var2 == null ? null : var1 + var2;
      }
   }

   private String oW(String var1) {
      if (this.HF == null) {
         return null;
      } else {
         Integer var2 = (Integer)this.HF.get(var1);
         return var2 == null ? null : this.JY[var2];
      }
   }

   private static String[] q(String[] var0) {
      String[] var1 = new String[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = var0[var2] + "--" + var0[var2] + "2--vector-";
      }

      return var1;
   }
}
