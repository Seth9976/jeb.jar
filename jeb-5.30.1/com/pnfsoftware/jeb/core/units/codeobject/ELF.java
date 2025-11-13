package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.lang.reflect.Field;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class ELF {
   private static final ILogger logger = GlobalLog.getLogger(ELF.class);
   public static byte[] ElfMagic = new byte[]{127, 69, 76, 70};
   public static int ElfMagicIntLE = 1179403647;
   public static int ElfMagicIntBE = 2135247942;
   public static final int ELF32_HEADER_SIZE_MIN = 52;
   public static final int ELF64_HEADER_SIZE_MIN = 64;
   public static final int ELF_HEADER_SIZE_MIN_SAFE = 64;
   public static final int PHT32_ENTRY_SIZE_MIN = 32;
   public static final int PHT64_ENTRY_SIZE_MIN = 56;
   public static final int SHT32_ENTRY_SIZE_MIN = 40;
   public static final int SHT64_ENTRY_SIZE_MIN = 64;
   public static final int SIZEOF_SYMBOL_ENTRY_32 = 16;
   public static final int SIZEOF_SYMBOL_ENTRY_64 = 24;
   public static final int SHN_UNDEF = 0;
   public static final int SHN_LORESERVE = 65280;
   public static final int SHN_LOPROC = 65280;
   public static final int SHN_HIPROC = 65311;
   public static final int SHN_LOOS = 65312;
   public static final int SHN_HIOS = 65343;
   public static final int SHN_ABS = 65521;
   public static final int SHN_COMMON = 65522;
   public static final int SHN_XINDEX = 65535;
   public static final int SHN_HIRESERVE = 65535;
   public static final int SHT_NULL = 0;
   public static final int SHT_PROGBITS = 1;
   public static final int SHT_SYMTAB = 2;
   public static final int SHT_STRTAB = 3;
   public static final int SHT_RELA = 4;
   public static final int SHT_HASH = 5;
   public static final int SHT_DYNAMIC = 6;
   public static final int SHT_NOTE = 7;
   public static final int SHT_NOBITS = 8;
   public static final int SHT_REL = 9;
   public static final int SHT_SHLIB = 10;
   public static final int SHT_DYNSYM = 11;
   public static final int SHT_INIT_ARRAY = 14;
   public static final int SHT_FINI_ARRAY = 15;
   public static final int SHT_PREINIT_ARRAY = 16;
   public static final int SHT_GROUP = 17;
   public static final int SHT_SYMTAB_SHNDX = 18;
   public static final int SHT_LOOS = 1610612736;
   public static final int SHT_GNU_ATTRIBUTES = 1879048181;
   public static final int SHT_GNU_HASH = 1879048182;
   public static final int SHT_GNU_LIBLIST = 1879048183;
   public static final int SHT_GNU_verdef = 1879048189;
   public static final int SHT_GNU_verneed = 1879048190;
   public static final int SHT_GNU_versym = 1879048191;
   public static final int SHT_HIOS = 1879048191;
   public static final int SHT_LOPROC = 1879048192;
   public static final int SHT_HIPROC = Integer.MAX_VALUE;
   public static final int SHT_LOUSER = Integer.MIN_VALUE;
   public static final int SHT_HIUSER = -1;
   public static final int SHT_ARM_EXIDX = 1879048193;
   public static final int SHT_ARM_PREEMPTMAP = 1879048194;
   public static final int SHT_ARM_ATTRIBUTES = 1879048195;
   public static final int SHT_ARM_DEBUGOVERLAY = 1879048196;
   public static final int SHT_ARM_OVERLAYSECTION = 1879048197;
   public static final int SHT_MIPS_REGINFO = 1879048198;
   public static final int SHT_MIPS_OPTIONS = 1879048205;
   public static final int SHT_MIPS_ABIFLAGS = 1879048234;
   public static final int SHF_WRITE = 1;
   public static final int SHF_ALLOC = 2;
   public static final int SHF_EXECINSTR = 4;
   public static final int SHF_MERGE = 16;
   public static final int SHF_STRINGS = 32;
   public static final int SHF_INFO_LINK = 64;
   public static final int SHF_LINK_ORDER = 128;
   public static final int SHF_OS_NONCONFORMING = 256;
   public static final int SHF_GROUP = 512;
   public static final int SHF_TLS = 1024;
   public static final int SHF_COMPRESSED = 2048;
   public static final int SHF_MASKOS = 267386880;
   public static final int SHF_MASKPROC = -268435456;
   public static final byte ELFCLASSNONE = 0;
   public static final byte ELFCLASS32 = 1;
   public static final byte ELFCLASS64 = 2;
   public static final byte ELFDATANONE = 0;
   public static final byte ELFDATA2LSB = 1;
   public static final byte ELFDATA2MSB = 2;
   public static final int EV_NONE = 0;
   public static final int EV_CURRENT = 1;
   public static final short ET_NONE = 0;
   public static final short ET_REL = 1;
   public static final short ET_EXEC = 2;
   public static final short ET_DYN = 3;
   public static final short ET_CORE = 4;
   public static final short ET_LOOS = -512;
   public static final short ET_HIOS = -257;
   public static final short ET_LOPROC = -256;
   public static final short ET_HIPROC = -1;
   public static final int ELFOSABI_NONE = 0;
   public static final int ELFOSABI_HPUX = 1;
   public static final int ELFOSABI_NETBSD = 2;
   public static final int ELFOSABI_GNU = 3;
   public static final int ELFOSABI_LINUX = 3;
   public static final int ELFOSABI_HURD = 4;
   public static final int ELFOSABI_SOLARIS = 6;
   public static final int ELFOSABI_AIX = 7;
   public static final int ELFOSABI_IRIX = 8;
   public static final int ELFOSABI_FREEBSD = 9;
   public static final int ELFOSABI_TRU64 = 10;
   public static final int ELFOSABI_MODESTO = 11;
   public static final int ELFOSABI_OPENBSD = 12;
   public static final int ELFOSABI_OPENVMS = 13;
   public static final int ELFOSABI_NSK = 14;
   public static final int ELFOSABI_AROS = 15;
   public static final int ELFOSABI_FENIXOS = 16;
   public static final int ELFOSABI_C6000_ELFABI = 64;
   public static final int ELFOSABI_C6000_LINUX = 65;
   public static final int ELFOSABI_ARM = 97;
   public static final int ELFOSABI_STANDALONE = 255;
   public static final int EM_NONE = 0;
   public static final int EM_M32 = 1;
   public static final int EM_SPARC = 2;
   public static final int EM_386 = 3;
   public static final int EM_68K = 4;
   public static final int EM_88K = 5;
   public static final int EM_486 = 6;
   public static final int EM_860 = 7;
   public static final int EM_MIPS = 8;
   public static final int EM_S370 = 9;
   public static final int EM_MIPS_RS3_LE = 10;
   public static final int EM_PARISC = 15;
   public static final int EM_VPP500 = 17;
   public static final int EM_SPARC32PLUS = 18;
   public static final int EM_960 = 19;
   public static final int EM_PPC = 20;
   public static final int EM_PPC64 = 21;
   public static final int EM_S390 = 22;
   public static final int EM_SPU = 23;
   public static final int EM_V800 = 36;
   public static final int EM_FR20 = 37;
   public static final int EM_RH32 = 38;
   public static final int EM_RCE = 39;
   public static final int EM_ARM = 40;
   public static final int EM_ALPHA = 41;
   public static final int EM_SH = 42;
   public static final int EM_SPARCV9 = 43;
   public static final int EM_TRICORE = 44;
   public static final int EM_ARC = 45;
   public static final int EM_H8_300 = 46;
   public static final int EM_H8_300H = 47;
   public static final int EM_H8S = 48;
   public static final int EM_H8_500 = 49;
   public static final int EM_IA_64 = 50;
   public static final int EM_MIPS_X = 51;
   public static final int EM_COLDFIRE = 52;
   public static final int EM_68HC12 = 53;
   public static final int EM_MMA = 54;
   public static final int EM_PCP = 55;
   public static final int EM_NCPU = 56;
   public static final int EM_NDR1 = 57;
   public static final int EM_STARCORE = 58;
   public static final int EM_ME16 = 59;
   public static final int EM_ST100 = 60;
   public static final int EM_TINYJ = 61;
   public static final int EM_X86_64 = 62;
   public static final int EM_PDSP = 63;
   public static final int EM_PDP10 = 64;
   public static final int EM_PDP11 = 65;
   public static final int EM_FX66 = 66;
   public static final int EM_ST9PLUS = 67;
   public static final int EM_ST7 = 68;
   public static final int EM_68HC16 = 69;
   public static final int EM_68HC11 = 70;
   public static final int EM_68HC08 = 71;
   public static final int EM_68HC05 = 72;
   public static final int EM_SVX = 73;
   public static final int EM_ST19 = 74;
   public static final int EM_VAX = 75;
   public static final int EM_CRIS = 76;
   public static final int EM_JAVELIN = 77;
   public static final int EM_FIREPATH = 78;
   public static final int EM_ZSP = 79;
   public static final int EM_MMIX = 80;
   public static final int EM_HUANY = 81;
   public static final int EM_PRISM = 82;
   public static final int EM_AVR = 83;
   public static final int EM_FR30 = 84;
   public static final int EM_D10V = 85;
   public static final int EM_D30V = 86;
   public static final int EM_V850 = 87;
   public static final int EM_M32R = 88;
   public static final int EM_MN10300 = 89;
   public static final int EM_MN10200 = 90;
   public static final int EM_PJ = 91;
   public static final int EM_OPENRISC = 92;
   public static final int EM_ARC_COMPACT = 93;
   public static final int EM_XTENSA = 94;
   public static final int EM_VIDEOCORE = 95;
   public static final int EM_TMM_GPP = 96;
   public static final int EM_NS32K = 97;
   public static final int EM_TPC = 98;
   public static final int EM_SNP1K = 99;
   public static final int EM_ST200 = 100;
   public static final int EM_IP2K = 101;
   public static final int EM_MAX = 102;
   public static final int EM_CR = 103;
   public static final int EM_F2MC16 = 104;
   public static final int EM_MSP430 = 105;
   public static final int EM_BLACKFIN = 106;
   public static final int EM_SE_C33 = 107;
   public static final int EM_SEP = 108;
   public static final int EM_ARCA = 109;
   public static final int EM_UNICORE = 110;
   public static final int EM_EXCESS = 111;
   public static final int EM_DXP = 112;
   public static final int EM_ALTERA_NIOS2 = 113;
   public static final int EM_CRX = 114;
   public static final int EM_XGATE = 115;
   public static final int EM_C166 = 116;
   public static final int EM_M16C = 117;
   public static final int EM_DSPIC30F = 118;
   public static final int EM_CE = 119;
   public static final int EM_M32C = 120;
   public static final int EM_TSK3000 = 131;
   public static final int EM_RS08 = 132;
   public static final int EM_SHARC = 133;
   public static final int EM_ECOG2 = 134;
   public static final int EM_SCORE7 = 135;
   public static final int EM_DSP24 = 136;
   public static final int EM_VIDEOCORE3 = 137;
   public static final int EM_LATTICEMICO32 = 138;
   public static final int EM_SE_C17 = 139;
   public static final int EM_TI_C6000 = 140;
   public static final int EM_TI_C2000 = 141;
   public static final int EM_TI_C5500 = 142;
   public static final int EM_MMDSP_PLUS = 160;
   public static final int EM_CYPRESS_M8C = 161;
   public static final int EM_R32C = 162;
   public static final int EM_TRIMEDIA = 163;
   public static final int EM_HEXAGON = 164;
   public static final int EM_8051 = 165;
   public static final int EM_STXP7X = 166;
   public static final int EM_NDS32 = 167;
   public static final int EM_ECOG1 = 168;
   public static final int EM_ECOG1X = 168;
   public static final int EM_MAXQ30 = 169;
   public static final int EM_XIMO16 = 170;
   public static final int EM_MANIK = 171;
   public static final int EM_CRAYNV2 = 172;
   public static final int EM_RX = 173;
   public static final int EM_METAG = 174;
   public static final int EM_MCST_ELBRUS = 175;
   public static final int EM_ECOG16 = 176;
   public static final int EM_CR16 = 177;
   public static final int EM_ETPU = 178;
   public static final int EM_SLE9X = 179;
   public static final int EM_L10M = 180;
   public static final int EM_K10M = 181;
   public static final int EM_AARCH64 = 183;
   public static final int EM_AVR32 = 185;
   public static final int EM_STM8 = 186;
   public static final int EM_TILE64 = 187;
   public static final int EM_TILEPRO = 188;
   public static final int EM_CUDA = 190;
   public static final int EM_TILEGX = 191;
   public static final int EM_CLOUDSHIELD = 192;
   public static final int EM_COREA_1ST = 193;
   public static final int EM_COREA_2ND = 194;
   public static final int EM_ARC_COMPACT2 = 195;
   public static final int EM_OPEN8 = 196;
   public static final int EM_RL78 = 197;
   public static final int EM_VIDEOCORE5 = 198;
   public static final int EM_78KOR = 199;
   public static final int EM_56800EX = 200;
   public static final int EM_RISCV = 243;
   public static final int EM_BPF = 247;
   public static final int EM_CSKY = 252;
   public static final int EM_LOONGARCH = 258;
   public static final int EM_FRV = 21569;
   private static volatile Map emToString;
   public static final int DT_NULL = 0;
   public static final int DT_NEEDED = 1;
   public static final int DT_PLTRELSZ = 2;
   public static final int DT_PLTGOT = 3;
   public static final int DT_HASH = 4;
   public static final int DT_STRTAB = 5;
   public static final int DT_SYMTAB = 6;
   public static final int DT_RELA = 7;
   public static final int DT_RELASZ = 8;
   public static final int DT_RELAENT = 9;
   public static final int DT_STRSZ = 10;
   public static final int DT_SYMENT = 11;
   public static final int DT_INIT = 12;
   public static final int DT_FINI = 13;
   public static final int DT_SONAME = 14;
   public static final int DT_RPATH = 15;
   public static final int DT_SYMBOLIC = 16;
   public static final int DT_REL = 17;
   public static final int DT_RELSZ = 18;
   public static final int DT_RELENT = 19;
   public static final int DT_PLTREL = 20;
   public static final int DT_DEBUG = 21;
   public static final int DT_TEXTREL = 22;
   public static final int DT_JMPREL = 23;
   public static final int DT_BIND_NOW = 24;
   public static final int DT_INIT_ARRAY = 25;
   public static final int DT_FINI_ARRAY = 26;
   public static final int DT_INIT_ARRAYSZ = 27;
   public static final int DT_FINI_ARRAYSZ = 28;
   public static final int DT_RUNPATH = 29;
   public static final int DT_FLAGS = 30;
   public static final int DT_PREINIT_ARRAY = 32;
   public static final int DT_PREINIT_ARRAYSZ = 33;
   public static final int DT_SYMTAB_SHNDX = 34;
   public static final int DT_LOOS = 1610612736;
   public static final int DT_HIOS = 1879048191;
   public static final int DT_LOPROC = 1879048192;
   public static final int DT_HIPROC = Integer.MAX_VALUE;
   public static final int DT_GNU_HASH = 1879047925;
   public static final int DT_RELACOUNT = 1879048185;
   public static final int DT_RELCOUNT = 1879048186;
   public static final int DT_FLAGS_1 = 1879048187;
   public static final int DT_VERSYM = 1879048176;
   public static final int DT_VERDEF = 1879048188;
   public static final int DT_VERDEFNUM = 1879048189;
   public static final int DT_VERNEED = 1879048190;
   public static final int DT_VERNEEDNUM = 1879048191;
   public static final int DT_AARCH64_BTI_PLT = 1879048193;
   public static final int DT_AARCH64_PAC_PLT = 1879048195;
   public static final int DT_AARCH64_VARIANT_PCS = 1879048197;
   private static final int DT_MIPS_RLD_VERSION = 1879048193;
   private static final int DT_MIPS_TIME_STAMP = 1879048194;
   private static final int DT_MIPS_ICHECKSUM = 1879048195;
   private static final int DT_MIPS_IVERSION = 1879048196;
   private static final int DT_MIPS_FLAGS = 1879048197;
   private static final int DT_MIPS_BASE_ADDRESS = 1879048198;
   private static final int DT_MIPS_MSYM = 1879048199;
   private static final int DT_MIPS_CONFLICT = 1879048200;
   private static final int DT_MIPS_LIBLIST = 1879048201;
   private static final int DT_MIPS_LOCAL_GOTNO = 1879048202;
   private static final int DT_MIPS_CONFLICTNO = 1879048203;
   private static final int DT_MIPS_LIBLISTNO = 1879048208;
   private static final int DT_MIPS_SYMTABNO = 1879048209;
   private static final int DT_MIPS_UNREFEXTNO = 1879048210;
   private static final int DT_MIPS_GOTSYM = 1879048211;
   private static final int DT_MIPS_HIPAGENO = 1879048212;
   private static final int DT_MIPS_RLD_MAP = 1879048214;
   private static final int DT_MIPS_DELTA_CLASS = 1879048215;
   private static final int DT_MIPS_DELTA_CLASS_NO = 1879048216;
   private static final int DT_MIPS_DELTA_INSTANCE = 1879048217;
   private static final int DT_MIPS_DELTA_INSTANCE_NO = 1879048218;
   private static final int DT_MIPS_DELTA_RELOC = 1879048219;
   private static final int DT_MIPS_DELTA_RELOC_NO = 1879048220;
   private static final int DT_MIPS_DELTA_SYM = 1879048221;
   private static final int DT_MIPS_DELTA_SYM_NO = 1879048222;
   private static final int DT_MIPS_DELTA_CLASSSYM = 1879048224;
   private static final int DT_MIPS_DELTA_CLASSSYM_NO = 1879048225;
   private static final int DT_MIPS_CXX_FLAGS = 1879048226;
   private static final int DT_MIPS_PIXIE_INIT = 1879048227;
   private static final int DT_MIPS_SYMBOL_LIB = 1879048228;
   private static final int DT_MIPS_LOCALPAGE_GOTIDX = 1879048229;
   private static final int DT_MIPS_LOCAL_GOTIDX = 1879048230;
   private static final int DT_MIPS_HIDDEN_GOTIDX = 1879048231;
   private static final int DT_MIPS_PROTECTED_GOTIDX = 1879048232;
   private static final int DT_MIPS_OPTIONS = 1879048233;
   private static final int DT_MIPS_INTERFACE = 1879048234;
   private static final int DT_MIPS_DYNSTR_ALIGN = 1879048235;
   private static final int DT_MIPS_INTERFACE_SIZE = 1879048236;
   private static final int DT_MIPS_RLD_TEXT_RESOLVE_ADDR = 1879048237;
   private static final int DT_MIPS_PERF_SUFFIX = 1879048238;
   private static final int DT_MIPS_COMPACT_SIZE = 1879048239;
   private static final int DT_MIPS_GP_VALUE = 1879048240;
   private static final int DT_MIPS_AUX_DYNAMIC = 1879048241;
   private static final int DT_MIPS_PLTGOT = 1879048242;
   private static final int DT_MIPS_RWPLT = 1879048244;
   public static final int PT_NULL = 0;
   public static final int PT_LOAD = 1;
   public static final int PT_DYNAMIC = 2;
   public static final int PT_INTERP = 3;
   public static final int PT_NOTE = 4;
   public static final int PT_SHLIB = 5;
   public static final int PT_PHDR = 6;
   public static final int PT_TLS = 7;
   public static final int PT_LOOS = 1610612736;
   public static final int PT_HIOS = 1879048191;
   public static final int PT_LOPROC = 1879048192;
   public static final int PT_HIPROC = Integer.MAX_VALUE;
   public static final int PT_GNU_EH_FRAME = 1685382480;
   public static final int PT_GNU_STACK = 1685382481;
   public static final int PT_GNU_RELRO = 1685382482;
   public static final int PT_GNU_PROPERTY = 1685382483;
   public static final int PT_ARM_EXIDX = 1879048193;
   public static final int PT_MIPS_REGINFO = 1879048192;
   public static final int PT_MIPS_RTPROC = 1879048193;
   public static final int PT_MIPS_OPTIONS = 1879048194;
   public static final int PT_HP_TLS = 1610612736;
   public static final int PT_HP_CORE_NONE = 1610612737;
   public static final int PT_HP_CORE_VERSION = 1610612738;
   public static final int PT_HP_CORE_KERNEL = 1610612739;
   public static final int PT_HP_CORE_COMM = 1610612740;
   public static final int PT_HP_CORE_PROC = 1610612741;
   public static final int PT_HP_CORE_LOADABLE = 1610612742;
   public static final int PT_HP_CORE_STACK = 1610612743;
   public static final int PT_HP_CORE_SHM = 1610612744;
   public static final int PT_HP_CORE_MMF = 1610612745;
   public static final int PT_HP_PARALLEL = 1610612752;
   public static final int PT_HP_FASTBIND = 1610612753;
   public static final int PT_HP_OPT_ANNOT = 1610612754;
   public static final int PT_HP_HSL_ANNOT = 1610612755;
   public static final int PT_HP_STACK = 1610612756;
   public static final int PT_HP_CORE_UTSNAME = 1610612757;
   public static final int PT_PARISC_ARCHEXT = 1879048192;
   public static final int PT_PARISC_UNWIND = 1879048193;
   public static final int PT_PARISC_WEAKORDER = 1879048194;
   public static final int PT_IA_64_ARCHEXT = 1879048192;
   public static final int PT_IA_64_UNWIND = 1879048193;
   public static final int PT_IA_64_HP_OPT_ANOT = 1610612754;
   public static final int PT_IA_64_HP_HSL_ANOT = 1610612755;
   public static final int PT_IA_64_HP_STACK = 1610612756;
   public static final int PF_X = 1;
   public static final int PF_W = 2;
   public static final int PF_R = 4;
   public static final int PF_MASKOS = 267386880;
   public static final int PF_MASKPROC = -268435456;
   public static final int STB_LOCAL = 0;
   public static final int STB_GLOBAL = 1;
   public static final int STB_WEAK = 2;
   public static final int STB_LOPROC = 13;
   public static final int STB_HIPROC = 15;
   public static final int STT_NOTYPE = 0;
   public static final int STT_OBJECT = 1;
   public static final int STT_FUNC = 2;
   public static final int STT_SECTION = 3;
   public static final int STT_FILE = 4;
   public static final int STT_COMMON = 5;
   public static final int STT_TLS = 6;
   public static final int STT_GNU_IFUNC = 10;
   public static final int STT_LOOS = 10;
   public static final int STT_HIOS = 12;
   public static final int STT_LOPROC = 13;
   public static final int STT_HIPROC = 15;
   public static final int STV_DEFAULT = 0;
   public static final int STV_INTERNAL = 1;
   public static final int STV_HIDDEN = 2;
   public static final int STV_PROTECTED = 3;
   public static final String NT_OWNER_FREEBSD = "FreeBSD";
   public static final String NT_OWNER_GNU = "GNU";
   public static final String NT_OWNER_NETBSD = "NetBSD";
   public static final String NT_OWNER_CSR = "csr";
   public static final String NT_OWNER_ANDROID = "Android";
   public static final int NT_GNU_ABI_TAG = 1;
   public static final int NT_GNU_HWCAP = 2;
   public static final int NT_GNU_BUILD_ID = 3;
   public static final int NT_GNU_GOLD_VERSION = 4;
   public static final int NT_GNU_PROPERTY_TYPE_0 = 5;
   public static final int ELF_NOTE_OS_LINUX = 0;
   public static final int ELF_NOTE_OS_GNU = 1;
   public static final int ELF_NOTE_OS_SOLARIS2 = 2;
   public static final int ELF_NOTE_OS_FREEBSD = 3;
   public static final byte ARM_ATTR_TAG_FILE = 1;
   public static final byte ARM_ATTR_TAG_SECTION = 2;
   public static final byte ARM_ATTR_TAG_SYMBOL = 3;
   public static final int ARM_ATTR_TAG_CPU_RAW_NAME = 4;
   public static final int ARM_ATTR_TAG_CPU_NAME = 5;
   public static final int ARM_ATTR_TAG_CPU_ARCH = 6;
   public static final int ARM_ATTR_TAG_CPU_ARCH_PROFILE = 7;
   public static final int ARM_ATTR_TAG_ARM_ISA_USE = 8;
   public static final int ARM_ATTR_TAG_THUMB_ISA_USE = 9;
   public static final int ARM_ATTR_TAG_FP_ARCH = 10;
   public static final int ARM_ATTR_TAG_WMMX_ARCH = 11;
   public static final int ARM_ATTR_TAG_ADVANCED_SIMD_ARCH = 12;
   public static final int ARM_ATTR_TAG_PCS_CONFIG = 13;
   public static final int ARM_ATTR_TAG_ABI_PCS_R9_USE = 14;
   public static final int ARM_ATTR_TAG_ABI_PCS_RW_DATA = 15;
   public static final int ARM_ATTR_TAG_ABI_PCS_RO_DATA = 16;
   public static final int ARM_ATTR_TAG_ABI_PCS_GOT_USE = 17;
   public static final int ARM_ATTR_TAG_ABI_PCS_WCHAR_T = 18;
   public static final int ARM_ATTR_TAG_ABI_FP_ROUNDING = 19;
   public static final int ARM_ATTR_TAG_ABI_FP_DENORMAL = 20;
   public static final int ARM_ATTR_TAG_ABI_FP_EXCEPTIONS = 21;
   public static final int ARM_ATTR_TAG_ABI_FP_USER_EXCEPTIONS = 22;
   public static final int ARM_ATTR_TAG_ABI_FP_NUMBER_MODEL = 23;
   public static final int ARM_ATTR_TAG_ABI_ALIGN_NEEDED = 24;
   public static final int ARM_ATTR_TAG_ABI_ALIGN_PRESERVED = 25;
   public static final int ARM_ATTR_TAG_ABI_ENUM_SIZE = 26;
   public static final int ARM_ATTR_TAG_ABI_HARDFP_USE = 27;
   public static final int ARM_ATTR_TAG_ABI_VFP_ARGS = 28;
   public static final int ARM_ATTR_TAG_ABI_WMMX_ARGS = 29;
   public static final int ARM_ATTR_TAG_ABI_OPTIMIZATION_GOALS = 30;
   public static final int ARM_ATTR_TAG_ABI_FP_OPTIMIZATION_GOALS = 31;
   public static final int ARM_ATTR_TAG_COMPATIBILITY = 32;
   public static final int ARM_ATTR_TAG_CPU_UNALIGNED_ACCESS = 34;
   public static final int ARM_ATTR_TAG_FP_HP_EXTENSION = 36;
   public static final int ARM_ATTR_TAG_ABI_FP_16BIT_FORMAT = 38;
   public static final int ARM_ATTR_TAG_MPEXTENSION_USE = 42;
   public static final int ARM_ATTR_TAG_DIV_USE = 44;
   public static final int ARM_ATTR_TAG_DSP_EXTENSION = 46;
   public static final int ARM_ATTR_TAG_ALSO_COMPATIBLE_WITH = 65;
   public static final int ARM_ATTR_TAG_CONFORMANCE = 67;
   public static final int ARM_ATTR_TAG_VIRTUALIZATION_USE = 68;
   public static final int ARM_ATTR_TAG_NODEFAULTS = 64;
   public static final int ARM_ATTR_TAG_T2EE_USE = 66;
   public static final int ARM_ATTR_TAG_MPEXTENSION_USE_OLD = 70;
   public static final int R_MIPS_NONE = 0;
   public static final int R_MIPS_16 = 1;
   public static final int R_MIPS_32 = 2;
   public static final int R_MIPS_REL32 = 3;
   public static final int R_MIPS_26 = 4;
   public static final int R_MIPS_HI16 = 5;
   public static final int R_MIPS_LO16 = 6;
   public static final int R_MIPS_GPREL16 = 7;
   public static final int R_MIPS_LITERAL = 8;
   public static final int R_MIPS_GOT16 = 9;
   public static final int R_MIPS_PC16 = 10;
   public static final int R_MIPS_CALL16 = 11;
   public static final int R_MIPS_GPREL32 = 12;
   public static final int R_MIPS_UNUSED1 = 13;
   public static final int R_MIPS_UNUSED2 = 14;
   public static final int R_MIPS_SHIFT5 = 16;
   public static final int R_MIPS_SHIFT6 = 17;
   public static final int R_MIPS_64 = 18;
   public static final int R_MIPS_GOT_DISP = 19;
   public static final int R_MIPS_GOT_PAGE = 20;
   public static final int R_MIPS_GOT_OFST = 21;
   public static final int R_MIPS_GOT_HI16 = 22;
   public static final int R_MIPS_GOT_LO16 = 23;
   public static final int R_MIPS_SUB = 24;
   public static final int R_MIPS_INSERT_A = 25;
   public static final int R_MIPS_INSERT_B = 26;
   public static final int R_MIPS_DELETE = 27;
   public static final int R_MIPS_HIGHER = 28;
   public static final int R_MIPS_HIGHEST = 29;
   public static final int R_MIPS_CALL_HI16 = 30;
   public static final int R_MIPS_CALL_LO16 = 31;
   public static final int R_MIPS_SCN_DISP = 32;
   public static final int R_MIPS_REL16 = 33;
   public static final int R_MIPS_ADD_IMMEDIATE = 34;
   public static final int R_MIPS_PJUMP = 35;
   public static final int R_MIPS_RELGOT = 36;
   public static final int R_MIPS_JALR = 37;
   public static final int R_MIPS_TLS_DTPMOD32 = 38;
   public static final int R_MIPS_TLS_DTPREL32 = 39;
   public static final int R_MIPS_TLS_DTPMOD64 = 40;
   public static final int R_MIPS_TLS_DTPREL64 = 41;
   public static final int R_MIPS_TLS_GD = 42;
   public static final int R_MIPS_TLS_LDM = 43;
   public static final int R_MIPS_TLS_DTPREL_HI16 = 44;
   public static final int R_MIPS_TLS_DTPREL_LO16 = 45;
   public static final int R_MIPS_TLS_GOTTPREL = 46;
   public static final int R_MIPS_TLS_TPREL32 = 47;
   public static final int R_MIPS_TLS_TPREL64 = 48;
   public static final int R_MIPS_TLS_TPREL_HI16 = 49;
   public static final int R_MIPS_TLS_TPREL_LO16 = 50;
   public static final int R_MIPS_GLOB_DAT = 51;
   public static final int R_MIPS_PC21_S2 = 60;
   public static final int R_MIPS_PC26_S2 = 61;
   public static final int R_MIPS_PC18_S3 = 62;
   public static final int R_MIPS_PC19_S2 = 63;
   public static final int R_MIPS_PCHI16 = 64;
   public static final int R_MIPS_PCLO16 = 65;
   public static final int R_MIPS16_GOT16 = 102;
   public static final int R_MIPS16_HI16 = 104;
   public static final int R_MIPS16_LO16 = 105;
   public static final int R_MIPS_COPY = 126;
   public static final int R_MIPS_JUMP_SLOT = 127;
   public static final int R_MICROMIPS_26_S1 = 133;
   public static final int R_MICROMIPS_HI16 = 134;
   public static final int R_MICROMIPS_LO16 = 135;
   public static final int R_MICROMIPS_GOT16 = 138;
   public static final int R_MICROMIPS_PC16_S1 = 141;
   public static final int R_MICROMIPS_CALL16 = 142;
   public static final int R_MICROMIPS_GOT_DISP = 145;
   public static final int R_MICROMIPS_GOT_PAGE = 146;
   public static final int R_MICROMIPS_GOT_OFST = 147;
   public static final int R_MICROMIPS_TLS_GD = 162;
   public static final int R_MICROMIPS_TLS_LDM = 163;
   public static final int R_MICROMIPS_TLS_DTPREL_HI16 = 164;
   public static final int R_MICROMIPS_TLS_DTPREL_LO16 = 165;
   public static final int R_MICROMIPS_TLS_TPREL_HI16 = 169;
   public static final int R_MICROMIPS_TLS_TPREL_LO16 = 170;
   public static final int R_MIPS_NUM = 218;
   public static final int R_MIPS_PC32 = 248;
   public static final int R_386_NONE = 0;
   public static final int R_386_32 = 1;
   public static final int R_386_PC32 = 2;
   public static final int R_386_GOT32 = 3;
   public static final int R_386_PLT32 = 4;
   public static final int R_386_COPY = 5;
   public static final int R_386_GLOB_DAT = 6;
   public static final int R_386_JMP_SLOT = 7;
   public static final int R_386_RELATIVE = 8;
   public static final int R_386_GOTOFF = 9;
   public static final int R_386_GOTPC = 10;
   public static final int R_386_TLS_TPOFF = 14;
   public static final int R_386_TLS_IE = 15;
   public static final int R_386_TLS_GOTIE = 16;
   public static final int R_386_TLS_LE = 17;
   public static final int R_386_TLS_GD = 18;
   public static final int R_386_TLS_LDM = 19;
   public static final int R_386_16 = 20;
   public static final int R_386_PC16 = 21;
   public static final int R_386_8 = 22;
   public static final int R_386_PC8 = 23;
   public static final int R_386_TLS_GD_32 = 24;
   public static final int R_386_TLS_GD_PUSH = 25;
   public static final int R_386_TLS_GD_CALL = 26;
   public static final int R_386_TLS_GD_POP = 27;
   public static final int R_386_TLS_LDM_32 = 28;
   public static final int R_386_TLS_LDM_PUSH = 29;
   public static final int R_386_TLS_LDM_CALL = 30;
   public static final int R_386_TLS_LDM_POP = 31;
   public static final int R_386_TLS_LDO_32 = 32;
   public static final int R_386_TLS_IE_32 = 33;
   public static final int R_386_TLS_LE_32 = 34;
   public static final int R_386_TLS_DTPMOD32 = 35;
   public static final int R_386_TLS_DTPOFF32 = 36;
   public static final int R_386_TLS_TPOFF32 = 37;
   public static final int R_386_TLS_GOTDESC = 39;
   public static final int R_386_TLS_DESC_CALL = 40;
   public static final int R_386_TLS_DESC = 41;
   public static final int R_386_IRELATIVE = 42;
   public static final int R_ARM_NONE = 0;
   public static final int R_ARM_PC24 = 1;
   public static final int R_ARM_ABS32 = 2;
   public static final int R_ARM_REL32 = 3;
   public static final int R_ARM_LDR_PC_G0 = 4;
   public static final int R_ARM_ABS16 = 5;
   public static final int R_ARM_ABS12 = 6;
   public static final int R_ARM_THM_ABS5 = 7;
   public static final int R_ARM_ABS8 = 8;
   public static final int R_ARM_SBREL32 = 9;
   public static final int R_ARM_THM_CALL = 10;
   public static final int R_ARM_THM_PC8 = 11;
   public static final int R_ARM_BREL_ADJ = 12;
   public static final int R_ARM_TLS_DESC = 13;
   public static final int R_ARM_THM_SWI8 = 14;
   public static final int R_ARM_XPC25 = 15;
   public static final int R_ARM_THM_XPC22 = 16;
   public static final int R_ARM_TLS_DTPMOD32 = 17;
   public static final int R_ARM_TLS_DTPOFF32 = 18;
   public static final int R_ARM_TLS_TPOFF32 = 19;
   public static final int R_ARM_COPY = 20;
   public static final int R_ARM_GLOB_DAT = 21;
   public static final int R_ARM_JUMP_SLOT = 22;
   public static final int R_ARM_RELATIVE = 23;
   public static final int R_ARM_GOTOFF32 = 24;
   public static final int R_ARM_BASE_PREL = 25;
   public static final int R_ARM_GOT_BREL = 26;
   public static final int R_ARM_PLT32 = 27;
   public static final int R_ARM_CALL = 28;
   public static final int R_ARM_JUMP24 = 29;
   public static final int R_ARM_THM_JUMP24 = 30;
   public static final int R_ARM_BASE_ABS = 31;
   public static final int R_ARM_ALU_PCREL_7_0 = 32;
   public static final int R_ARM_ALU_PCREL_15_8 = 33;
   public static final int R_ARM_ALU_PCREL_23_15 = 34;
   public static final int R_ARM_LDR_SBREL_11_0_NC = 35;
   public static final int R_ARM_ALU_SBREL_19_12_NC = 36;
   public static final int R_ARM_ALU_SBREL_27_20_CK = 37;
   public static final int R_ARM_TARGET1 = 38;
   public static final int R_ARM_SBREL31 = 39;
   public static final int R_ARM_V4BX = 40;
   public static final int R_ARM_TARGET2 = 41;
   public static final int R_ARM_PREL31 = 42;
   public static final int R_ARM_MOVW_ABS_NC = 43;
   public static final int R_ARM_MOVT_ABS = 44;
   public static final int R_ARM_MOVW_PREL_NC = 45;
   public static final int R_ARM_MOVT_PREL = 46;
   public static final int R_ARM_THM_MOVW_ABS_NC = 47;
   public static final int R_ARM_THM_MOVT_ABS = 48;
   public static final int R_ARM_THM_MOVW_PREL_NC = 49;
   public static final int R_ARM_THM_MOVT_PREL = 50;
   public static final int R_ARM_THM_JUMP19 = 51;
   public static final int R_ARM_THM_JUMP6 = 52;
   public static final int R_ARM_THM_ALU_PREL_11_0 = 53;
   public static final int R_ARM_THM_PC12 = 54;
   public static final int R_ARM_ABS32_NOI = 55;
   public static final int R_ARM_REL32_NOI = 56;
   public static final int R_ARM_ALU_PC_G0_NC = 57;
   public static final int R_ARM_ALU_PC_G0 = 58;
   public static final int R_ARM_ALU_PC_G1_NC = 59;
   public static final int R_ARM_ALU_PC_G1 = 60;
   public static final int R_ARM_ALU_PC_G2 = 61;
   public static final int R_ARM_LDR_PC_G1 = 62;
   public static final int R_ARM_LDR_PC_G2 = 63;
   public static final int R_ARM_LDRS_PC_G0 = 64;
   public static final int R_ARM_LDRS_PC_G1 = 65;
   public static final int R_ARM_LDRS_PC_G2 = 66;
   public static final int R_ARM_LDC_PC_G0 = 67;
   public static final int R_ARM_LDC_PC_G1 = 68;
   public static final int R_ARM_LDC_PC_G2 = 69;
   public static final int R_ARM_ALU_SB_G0_NC = 70;
   public static final int R_ARM_ALU_SB_G0 = 71;
   public static final int R_ARM_ALU_SB_G1_NC = 72;
   public static final int R_ARM_ALU_SB_G1 = 73;
   public static final int R_ARM_ALU_SB_G2 = 74;
   public static final int R_ARM_LDR_SB_G0 = 75;
   public static final int R_ARM_LDR_SB_G1 = 76;
   public static final int R_ARM_LDR_SB_G2 = 77;
   public static final int R_ARM_LDRS_SB_G0 = 78;
   public static final int R_ARM_LDRS_SB_G1 = 79;
   public static final int R_ARM_LDRS_SB_G2 = 80;
   public static final int R_ARM_LDC_SB_G0 = 81;
   public static final int R_ARM_LDC_SB_G1 = 82;
   public static final int R_ARM_LDC_SB_G2 = 83;
   public static final int R_ARM_MOVW_BREL_NC = 84;
   public static final int R_ARM_MOVT_BREL = 85;
   public static final int R_ARM_MOVW_BREL = 86;
   public static final int R_ARM_THM_MOVW_BREL_NC = 87;
   public static final int R_ARM_THM_MOVT_BREL = 88;
   public static final int R_ARM_THM_MOVW_BREL = 89;
   public static final int R_ARM_TLS_GOTDESC = 90;
   public static final int R_ARM_TLS_CALL = 91;
   public static final int R_ARM_TLS_DESCSEQ = 92;
   public static final int R_ARM_THM_TLS_CALL = 93;
   public static final int R_ARM_PLT32_ABS = 94;
   public static final int R_ARM_GOT_ABS = 95;
   public static final int R_ARM_GOT_PREL = 96;
   public static final int R_ARM_GOT_BREL12 = 97;
   public static final int R_ARM_GOTOFF12 = 98;
   public static final int R_ARM_GOTRELAX = 99;
   public static final int R_ARM_GNU_VTENTRY = 100;
   public static final int R_ARM_GNU_VTINHERIT = 101;
   public static final int R_ARM_THM_JUMP11 = 102;
   public static final int R_ARM_THM_JUMP8 = 103;
   public static final int R_ARM_TLS_GD32 = 104;
   public static final int R_ARM_TLS_LDM32 = 105;
   public static final int R_ARM_TLS_LDO32 = 106;
   public static final int R_ARM_TLS_IE32 = 107;
   public static final int R_ARM_TLS_LE32 = 108;
   public static final int R_ARM_TLS_LDO12 = 109;
   public static final int R_ARM_TLS_LE12 = 110;
   public static final int R_ARM_TLS_IE12GP = 111;
   public static final int R_ARM_PRIVATE_0 = 112;
   public static final int R_ARM_PRIVATE_1 = 113;
   public static final int R_ARM_PRIVATE_2 = 114;
   public static final int R_ARM_PRIVATE_3 = 115;
   public static final int R_ARM_PRIVATE_4 = 116;
   public static final int R_ARM_PRIVATE_5 = 117;
   public static final int R_ARM_PRIVATE_6 = 118;
   public static final int R_ARM_PRIVATE_7 = 119;
   public static final int R_ARM_PRIVATE_8 = 120;
   public static final int R_ARM_PRIVATE_9 = 121;
   public static final int R_ARM_PRIVATE_10 = 122;
   public static final int R_ARM_PRIVATE_11 = 123;
   public static final int R_ARM_PRIVATE_12 = 124;
   public static final int R_ARM_PRIVATE_13 = 125;
   public static final int R_ARM_PRIVATE_14 = 126;
   public static final int R_ARM_PRIVATE_15 = 127;
   public static final int R_ARM_ME_TOO = 128;
   public static final int R_ARM_THM_TLS_DESCSEQ16 = 129;
   public static final int R_ARM_THM_TLS_DESCSEQ32 = 130;
   public static final int R_AARCH64_NONE_ = 0;
   public static final int R_AARCH64_NONE = 256;
   public static final int R_AARCH64_ABS64 = 257;
   public static final int R_AARCH64_ABS32 = 258;
   public static final int R_AARCH64_ABS16 = 259;
   public static final int R_AARCH64_PREL64 = 260;
   public static final int R_AARCH64_PREL32 = 261;
   public static final int R_AARCH64_PREL16 = 262;
   public static final int R_AARCH64_MOVW_UABS_G0 = 263;
   public static final int R_AARCH64_MOVW_UABS_G0_NC = 264;
   public static final int R_AARCH64_MOVW_UABS_G1 = 265;
   public static final int R_AARCH64_MOVW_UABS_G1_NC = 266;
   public static final int R_AARCH64_MOVW_UABS_G2 = 267;
   public static final int R_AARCH64_MOVW_UABS_G2_NC = 268;
   public static final int R_AARCH64_MOVW_UABS_G3 = 269;
   public static final int R_AARCH64_MOVW_SABS_G0 = 270;
   public static final int R_AARCH64_MOVW_SABS_G1 = 271;
   public static final int R_AARCH64_MOVW_SABS_G2 = 272;
   public static final int R_AARCH64_LD_PREL_LO19 = 273;
   public static final int R_AARCH64_ADR_PREL_LO21 = 274;
   public static final int R_AARCH64_ADR_PREL_PG_HI21 = 275;
   public static final int R_AARCH64_ADR_PREL_PG_HI21_NC = 276;
   public static final int R_AARCH64_ADD_ABS_LO12_NC = 277;
   public static final int R_AARCH64_LDST8_ABS_LO12_NC = 278;
   public static final int R_AARCH64_TSTBR14 = 279;
   public static final int R_AARCH64_CONDBR19 = 280;
   public static final int R_AARCH64_JUMP26 = 282;
   public static final int R_AARCH64_CALL26 = 283;
   public static final int R_AARCH64_LDST16_ABS_LO12_NC = 284;
   public static final int R_AARCH64_LDST32_ABS_LO12_NC = 285;
   public static final int R_AARCH64_LDST64_ABS_LO12_NC = 286;
   public static final int R_AARCH64_LDST128_ABS_LO12_NC = 299;
   public static final int R_AARCH64_MOVW_PREL_G0 = 287;
   public static final int R_AARCH64_MOVW_PREL_G0_NC = 288;
   public static final int R_AARCH64_MOVW_PREL_G1 = 289;
   public static final int R_AARCH64_MOVW_PREL_G1_NC = 290;
   public static final int R_AARCH64_MOVW_PREL_G2 = 291;
   public static final int R_AARCH64_MOVW_PREL_G2_NC = 292;
   public static final int R_AARCH64_MOVW_PREL_G3 = 293;
   public static final int R_AARCH64_COPY = 1024;
   public static final int R_AARCH64_GLOB_DAT = 1025;
   public static final int R_AARCH64_JUMP_SLOT = 1026;
   public static final int R_AARCH64_RELATIVE = 1027;
   public static final int R_AARCH64_TLS_TPREL64 = 1030;
   public static final int R_AARCH64_TLS_DTPREL32 = 1031;
   public static final int R_AARCH64_IRELATIVE = 1032;
   public static final int R_X86_64_NONE = 0;
   public static final int R_X86_64_64 = 1;
   public static final int R_X86_64_PC32 = 2;
   public static final int R_X86_64_GOT32 = 3;
   public static final int R_X86_64_PLT32 = 4;
   public static final int R_X86_64_COPY = 5;
   public static final int R_X86_64_GLOB_DAT = 6;
   public static final int R_X86_64_JUMP_SLOT = 7;
   public static final int R_X86_64_RELATIVE = 8;
   public static final int R_X86_64_GOTPCREL = 9;
   public static final int R_X86_64_32 = 10;
   public static final int R_X86_64_32S = 11;
   public static final int R_X86_64_16 = 12;
   public static final int R_X86_64_PC16 = 13;
   public static final int R_X86_64_8 = 14;
   public static final int R_X86_64_PC8 = 15;
   public static final int R_X86_64_DTPMOD64 = 16;
   public static final int R_X86_64_DTPOFF64 = 17;
   public static final int R_X86_64_TPOFF64 = 18;
   public static final int R_X86_64_TLSGD = 19;
   public static final int R_X86_64_TLSLD = 20;
   public static final int R_X86_64_DTPOFF32 = 21;
   public static final int R_X86_64_GOTTPOFF = 22;
   public static final int R_X86_64_TPOFF32 = 23;
   public static final int R_X86_64_PC64 = 24;
   public static final int R_X86_64_GOTOFF64 = 25;
   public static final int R_X86_64_GOTPC32 = 26;
   public static final int R_X86_64_GOT64 = 27;
   public static final int R_X86_64_GOTPCREL64 = 28;
   public static final int R_X86_64_GOTPC64 = 29;
   public static final int R_X86_64_GOTPLT64 = 30;
   public static final int R_X86_64_PLTOFF64 = 31;
   public static final int R_X86_64_SIZE32 = 32;
   public static final int R_X86_64_SIZE64 = 33;
   public static final int R_X86_64_GOTPC32_TLSDESC = 34;
   public static final int R_X86_64_TLSDESC_CALL = 35;
   public static final int R_X86_64_TLSDESC = 36;
   public static final int R_X86_64_IRELATIVE = 37;
   public static final int R_X86_64_RELATIVE64 = 38;
   public static final int R_X86_64_NUM = 39;

   public static String getSHNString(int var0) {
      switch (var0) {
         case 0:
            return "UNDEF";
         case 65521:
            return "ABS";
         case 65522:
            return "COMMON";
         case 65535:
            return "XINDEX";
         default:
            return Integer.toHexString(var0);
      }
   }

   public static String getSHTString(int var0) {
      switch (var0) {
         case Integer.MIN_VALUE:
            return "LOUSER";
         case -1:
            return "HIUSER";
         case 0:
            return "NULL";
         case 1:
            return "PROGBITS";
         case 2:
            return "SYMTAB";
         case 3:
            return "STRTAB";
         case 4:
            return "RELA";
         case 5:
            return "HASH";
         case 6:
            return "DYNAMIC";
         case 7:
            return "NOTE";
         case 8:
            return "NOBITS";
         case 9:
            return "REL";
         case 10:
            return "SHLIB";
         case 11:
            return "DYNSYM";
         case 14:
            return "INIT_ARRAY";
         case 15:
            return "FINI_ARRAY";
         case 16:
            return "PREINIT_ARRAY";
         case 17:
            return "GROUP";
         case 18:
            return "SYMTAB_SHNDX";
         case 1610612736:
            return "LOOS";
         case 1879048181:
            return "GNU_ATTRIBUTES";
         case 1879048182:
            return "GNU_HASH";
         case 1879048191:
            return "HIOS";
         case 1879048192:
            return "LOPROC";
         case Integer.MAX_VALUE:
            return "HIPROC";
         default:
            return Integer.toHexString(var0);
      }
   }

   public static String getSHFStringFlags(int var0) {
      StringBuilder var1 = new StringBuilder();

      for (byte var2 = 1; var2 != 0 && var0 != 0; var2 <<= 1) {
         if ((var0 & var2) != 0) {
            if (var1.length() > 0) {
               var1.append('|');
            }

            var1.append(getSHFString(var0 & var2));
            var0 &= ~var2;
         }
      }

      return var1.toString();
   }

   public static String getSHFString(int var0) {
      switch (var0) {
         case -268435456:
            return "MASKPROC";
         case 1:
            return "WRITE";
         case 2:
            return "ALLOC";
         case 4:
            return "EXECINSTR";
         case 16:
            return "MERGE";
         case 32:
            return "STRINGS";
         case 64:
            return "INFO_LINK";
         case 128:
            return "LINK_ORDER";
         case 256:
            return "OS_NONCONFORMING";
         case 512:
            return "GROUP";
         case 1024:
            return "TLS";
         case 2048:
            return "COMPRESSED";
         case 267386880:
            return "MASKOS";
         default:
            return Integer.toHexString(var0);
      }
   }

   public static ELF.WellKnownSection getSection(int var0, String var1) {
      for (ELF.WellKnownSection var5 : ELF.WellKnownSection.values()) {
         if (var5.type == var0 && var5.name.equals(var1)) {
            return var5;
         }
      }

      return null;
   }

   public static String getELFClassString(int var0) {
      switch (var0) {
         case 0:
            return "ELFNONE";
         case 1:
            return "ELF32";
         case 2:
            return "ELF64";
         default:
            return Integer.toHexString(var0);
      }
   }

   public static String getELFDataString(int var0) {
      switch (var0) {
         case 0:
            return "NONE";
         case 1:
            return "2's complement, little endian";
         case 2:
            return "2's complement, big endian";
         default:
            return Integer.toHexString(var0);
      }
   }

   public static String getEVString(int var0) {
      switch (var0) {
         case 0:
            return "NONE";
         case 1:
            return "1 (CURRENT)";
         default:
            return Integer.toHexString(var0);
      }
   }

   public static String getETString(int var0) {
      switch (var0) {
         case 0:
            return "NONE (None)";
         case 1:
            return "REL (Relocatable file)";
         case 2:
            return "EXEC (Executable file)";
         case 3:
            return "DYN (Shared object file)";
         case 4:
            return "CORE (Core file)";
         default:
            return Integer.toHexString(var0);
      }
   }

   public static String getOSABIString(int var0) {
      switch (var0) {
         case 0:
            return "UNIX - System V";
         case 1:
            return "UNIX - HP-UX";
         case 2:
            return "UNIX - NetBSD";
         case 3:
            return "UNIX - GNU";
         case 4:
         case 5:
         default:
            return "Unknown";
         case 6:
            return "UNIX - Solaris";
         case 7:
            return "UNIX - AIX";
         case 8:
            return "UNIX - IRIX";
         case 9:
            return "UNIX - FreeBSD";
         case 10:
            return "UNIX - TRU64";
         case 11:
            return "Novell - Modesto";
         case 12:
            return "UNIX - OpenBSD";
         case 13:
            return "VMS - OpenVMS";
         case 14:
            return "HP - Non-Stop Kernel";
         case 15:
            return "AROS";
         case 16:
            return "FenixOS";
      }
   }

   public static String getEMString(int var0) {
      switch (var0) {
         case 0:
            return "None";
         case 1:
            return "WE32100";
         case 2:
            return "Sparc";
         case 3:
            return "Intel 80386";
         case 4:
            return "MC68000";
         case 5:
            return "MC88000";
         case 6:
            return "Intel 80486";
         case 7:
            return "Intel 80860";
         case 8:
            return "MIPS R3000";
         case 9:
            return "IBM System/370";
         case 10:
            return "MIPS R3000 little-endian (or R4000 big-endian)";
         case 11:
         case 12:
         case 13:
         case 14:
         case 16:
         case 17:
         case 24:
         case 25:
         case 26:
         case 27:
         case 28:
         case 29:
         case 30:
         case 31:
         case 32:
         case 33:
         case 34:
         case 35:
         case 39:
         case 62:
         case 92:
         case 93:
         case 99:
         case 101:
         case 114:
         case 121:
         case 122:
         case 123:
         case 124:
         case 125:
         case 126:
         case 127:
         case 128:
         case 129:
         case 130:
         case 133:
         case 135:
         case 143:
         case 144:
         case 145:
         case 146:
         case 147:
         case 148:
         case 149:
         case 150:
         case 151:
         case 152:
         case 153:
         case 154:
         case 155:
         case 156:
         case 157:
         case 158:
         case 159:
         case 164:
         case 180:
         case 181:
         case 182:
         case 184:
         case 189:
         case 192:
         case 193:
         case 194:
         case 195:
         case 196:
         default:
            if (emToString == null) {
               synchronized (ELF.class) {
                  if (emToString == null) {
                     HashMap var2 = new HashMap();

                     for (Field var6 : ELF.class.getDeclaredFields()) {
                        if (var6.getName().startsWith("EM_")) {
                           String var7 = var6.getName().substring(3);

                           try {
                              int var8 = var6.getInt(null);
                              var2.put(var8, var7);
                           } catch (Exception var10) {
                           }
                        }
                     }

                     emToString = var2;
                  }
               }
            }

            return (String)emToString.getOrDefault(var0, "Unknown");
         case 15:
            return "HPPA";
         case 18:
            return "Sparc v8+";
         case 19:
            return "Intel 90860";
         case 20:
            return "PowerPC";
         case 21:
            return "PowerPC64";
         case 22:
            return "IBM S/390";
         case 23:
            return "SPU";
         case 36:
            return "Renesas V850 (using RH850 ABI)";
         case 37:
            return "Fujitsu FR20";
         case 38:
            return "TRW RH32";
         case 40:
            return "ARM";
         case 41:
            return "Alpha";
         case 42:
            return "Renesas / SuperH SH";
         case 43:
            return "Sparc v9";
         case 44:
            return "Siemens Tricore";
         case 45:
            return "ARC";
         case 46:
            return "Renesas H8/300";
         case 47:
            return "Renesas H8/300H";
         case 48:
            return "Renesas H8S";
         case 49:
            return "Renesas H8/500";
         case 50:
            return "Intel IA-64";
         case 51:
            return "Stanford MIPS-X";
         case 52:
            return "Motorola Coldfire";
         case 53:
            return "Motorola MC68HC12 Microcontroller";
         case 54:
            return "Fujitsu Multimedia Accelerator";
         case 55:
            return "Siemens PCP";
         case 56:
            return "Sony nCPU embedded RISC processor";
         case 57:
            return "Denso NDR1 microprocesspr";
         case 58:
            return "Motorola Star*Core processor";
         case 59:
            return "Toyota ME16 processor";
         case 60:
            return "STMicroelectronics ST100 processor";
         case 61:
            return "Advanced Logic Corp. TinyJ embedded processor";
         case 63:
            return "Sony DSP processor";
         case 64:
            return "Digital Equipment Corp. PDP-10";
         case 65:
            return "Digital Equipment Corp. PDP-11";
         case 66:
            return "Siemens FX66 microcontroller";
         case 67:
            return "STMicroelectronics ST9+ 8/16 bit microcontroller";
         case 68:
            return "STMicroelectronics ST7 8-bit microcontroller";
         case 69:
            return "Motorola MC68HC16 Microcontroller";
         case 70:
            return "Motorola MC68HC11 Microcontroller";
         case 71:
            return "Motorola MC68HC08 Microcontroller";
         case 72:
            return "Motorola MC68HC05 Microcontroller";
         case 73:
            return "Silicon Graphics SVx";
         case 74:
            return "STMicroelectronics ST19 8-bit microcontroller";
         case 75:
            return "Digital VAX";
         case 76:
            return "Axis Communications 32-bit embedded processor";
         case 77:
            return "Infineon Technologies 32-bit embedded cpu";
         case 78:
            return "Element 14 64-bit DSP processor";
         case 79:
            return "LSI Logic's 16-bit DSP processor";
         case 80:
            return "Donald Knuth's educational 64-bit processor";
         case 81:
            return "Harvard Universitys's machine-independent object format";
         case 82:
            return "Vitesse Prism";
         case 83:
            return "Atmel AVR 8-bit microcontroller";
         case 84:
            return "Fujitsu FR30";
         case 85:
            return "d10v";
         case 86:
            return "d30v";
         case 87:
            return "Renesas V850";
         case 88:
            return "Renesas M32R (formerly Mitsubishi M32r)";
         case 89:
            return "mn10300";
         case 90:
            return "mn10200";
         case 91:
            return "picoJava";
         case 94:
            return "Tensilica Xtensa Processor";
         case 95:
            return "Alphamosaic VideoCore processor";
         case 96:
            return "Thompson Multimedia General Purpose Processor";
         case 97:
            return "National Semiconductor 32000 series";
         case 98:
            return "Tenor Network TPC processor";
         case 100:
            return "STMicroelectronics ST200 microcontroller";
         case 102:
            return "MAX Processor";
         case 103:
            return "National Semiconductor CompactRISC";
         case 104:
            return "Fujitsu F2MC16";
         case 105:
            return "Texas Instruments msp430 microcontroller";
         case 106:
            return "Analog Devices Blackfin";
         case 107:
            return "S1C33 Family of Seiko Epson processors";
         case 108:
            return "Sharp embedded microprocessor";
         case 109:
            return "Arca RISC microprocessor";
         case 110:
            return "Unicore";
         case 111:
            return "eXcess 16/32/64-bit configurable embedded CPU";
         case 112:
            return "Icera Semiconductor Inc. Deep Execution Processor";
         case 113:
            return "Altera Nios II";
         case 115:
            return "Motorola XGATE embedded processor";
         case 116:
         case 117:
            return "Renesas M16C series microprocessors";
         case 118:
            return "Microchip Technology dsPIC30F Digital Signal Controller";
         case 119:
            return "Freescale Communication Engine RISC core";
         case 120:
            return "Renesas M32c";
         case 131:
            return "Altium TSK3000 core";
         case 132:
            return "Freescale RS08 embedded processor";
         case 134:
            return "Cyan Technology eCOG2 microprocessor";
         case 136:
            return "New Japan Radio (NJR) 24-bit DSP Processor";
         case 137:
            return "Broadcom VideoCore III processor";
         case 138:
            return "Lattice Mico32";
         case 139:
            return "Seiko Epson C17 family";
         case 140:
            return "Texas Instruments TMS320C6000 DSP family";
         case 141:
            return "Texas Instruments TMS320C2000 DSP family";
         case 142:
            return "Texas Instruments TMS320C55x DSP family";
         case 160:
            return "STMicroelectronics 64bit VLIW Data Signal Processor";
         case 161:
            return "Cypress M8C microprocessor";
         case 162:
            return "Renesas R32C series microprocessors";
         case 163:
            return "NXP Semiconductors TriMedia architecture family";
         case 165:
            return "Intel 8051 and variants";
         case 166:
            return "STMicroelectronics STxP7x family";
         case 167:
            return "Andes Technology compact code size embedded RISC processor family";
         case 168:
            return "Cyan Technology eCOG1X family";
         case 169:
            return "Dallas Semiconductor MAXQ30 Core microcontrollers";
         case 170:
            return "New Japan Radio (NJR) 16-bit DSP Processor";
         case 171:
            return "M2000 Reconfigurable RISC Microprocessor";
         case 172:
            return "Cray Inc. NV2 vector architecture";
         case 173:
            return "Renesas RX";
         case 174:
            return "Imagination Technologies Meta processor architecture";
         case 175:
            return "MCST Elbrus general purpose hardware architecture";
         case 176:
            return "Cyan Technology eCOG16 family";
         case 177:
         case 197:
            return "Renesas RL78";
         case 178:
            return "Freescale Extended Time Processing Unit";
         case 179:
            return "Infineon Technologies SLE9X core";
         case 183:
            return "AArch64";
         case 185:
            return "Atmel Corporation 32-bit microprocessor family";
         case 186:
            return "STMicroeletronics STM8 8-bit microcontroller";
         case 187:
            return "Tilera TILE64 multicore architecture family";
         case 188:
            return "Tilera TILEPro multicore architecture family";
         case 190:
            return "NVIDIA CUDA architecture";
         case 191:
            return "Tilera TILE-Gx multicore architecture family";
      }
   }

   public static String getDT(int var0) {
      switch (var0) {
         case 0:
            return "NULL";
         case 1:
            return "NEEDED";
         case 2:
            return "PLTRELSZ";
         case 3:
            return "PLTGOT";
         case 4:
            return "HASH";
         case 5:
            return "STRTAB";
         case 6:
            return "SYMTAB";
         case 7:
            return "RELA";
         case 8:
            return "RELASZ";
         case 9:
            return "RELAENT";
         case 10:
            return "STRSZ";
         case 11:
            return "SYMENT";
         case 12:
            return "INIT";
         case 13:
            return "FINI";
         case 14:
            return "SONAME";
         case 15:
            return "RPATH";
         case 16:
            return "SYMBOLIC";
         case 17:
            return "REL";
         case 18:
            return "RELSZ";
         case 19:
            return "RELENT";
         case 20:
            return "PLTREL";
         case 21:
            return "DEBUG";
         case 22:
            return "TEXTREL";
         case 23:
            return "JMPREL";
         case 24:
            return "BIND_NOW";
         case 25:
            return "INIT_ARRAY";
         case 26:
            return "FINI_ARRAY";
         case 27:
            return "INIT_ARRAYSZ";
         case 28:
            return "FINI_ARRAYSZ";
         case 29:
            return "RUNPATH";
         case 30:
            return "FLAGS";
         case 32:
            return "PREINIT_ARRAY";
         case 33:
            return "PREINIT_ARRAYSZ";
         case 1879047925:
            return "GNU_HASH";
         case 1879048176:
            return "VERSYM";
         case 1879048185:
            return "RELACOUNT";
         case 1879048186:
            return "RELCOUNT";
         case 1879048187:
            return "FLAGS_1";
         case 1879048188:
            return "VERDEF";
         case 1879048189:
            return "VERDEFNUM";
         case 1879048190:
            return "VERNEED";
         case 1879048191:
            return "VERNEEDNUM";
         case 1879048193:
            return "MIPS_RLD_VERSION";
         case 1879048194:
            return "MIPS_TIME_STAMP";
         case 1879048195:
            return "MIPS_ICHECKSUM";
         case 1879048196:
            return "MIPS_IVERSION";
         case 1879048197:
            return "MIPS_FLAGS";
         case 1879048198:
            return "MIPS_BASE_ADDRESS";
         case 1879048199:
            return "MIPS_MSYM";
         case 1879048200:
            return "MIPS_CONFLICT";
         case 1879048201:
            return "MIPS_LIBLIST";
         case 1879048202:
            return "MIPS_LOCAL_GOTNO";
         case 1879048203:
            return "MIPS_CONFLICTNO";
         case 1879048208:
            return "MIPS_LIBLISTNO";
         case 1879048209:
            return "MIPS_SYMTABNO";
         case 1879048210:
            return "MIPS_UNREFEXTNO";
         case 1879048211:
            return "MIPS_GOTSYM";
         case 1879048212:
            return "MIPS_HIPAGENO";
         case 1879048214:
            return "MIPS_RLD_MAP";
         case 1879048215:
            return "MIPS_DELTA_CLASS";
         case 1879048216:
            return "MIPS_DELTA_CLASS_NO";
         case 1879048217:
            return "MIPS_DELTA_INSTANCE";
         case 1879048218:
            return "MIPS_DELTA_INSTANCE_NO";
         case 1879048219:
            return "MIPS_DELTA_RELOC";
         case 1879048220:
            return "MIPS_DELTA_RELOC_NO";
         case 1879048221:
            return "MIPS_DELTA_SYM";
         case 1879048222:
            return "MIPS_DELTA_SYM_NO";
         case 1879048224:
            return "MIPS_DELTA_CLASSSYM";
         case 1879048225:
            return "MIPS_DELTA_CLASSSYM_NO";
         case 1879048226:
            return "MIPS_CXX_FLAGS";
         case 1879048227:
            return "MIPS_PIXIE_INIT";
         case 1879048228:
            return "MIPS_SYMBOL_LIB";
         case 1879048229:
            return "MIPS_LOCALPAGE_GOTIDX";
         case 1879048230:
            return "MIPS_LOCAL_GOTIDX";
         case 1879048231:
            return "MIPS_HIDDEN_GOTIDX";
         case 1879048232:
            return "MIPS_PROTECTED_GOTIDX";
         case 1879048233:
            return "MIPS_OPTIONS";
         case 1879048234:
            return "MIPS_INTERFACE";
         case 1879048235:
            return "MIPS_DYNSTR_ALIGN";
         case 1879048236:
            return "MIPS_INTERFACE_SIZE";
         case 1879048237:
            return "MIPS_RLD_TEXT_RESOLVE_ADDR";
         case 1879048238:
            return "MIPS_PERF_SUFFIX";
         case 1879048239:
            return "MIPS_COMPACT_SIZE";
         case 1879048240:
            return "MIPS_GP_VALUE";
         case 1879048241:
            return "MIPS_AUX_DYNAMIC";
         case 1879048242:
            return "MIPS_PLTGOT";
         case 1879048244:
            return "MIPS_RWPLT";
         default:
            return Integer.toHexString(var0);
      }
   }

   public static String getPTString(int var0) {
      return getPTString(var0, -1);
   }

   public static String getPTString(int var0, int var1) {
      switch (var0) {
         case 0:
            return "NULL";
         case 1:
            return "LOAD";
         case 2:
            return "DYNAMIC";
         case 3:
            return "INTERP";
         case 4:
            return "NOTE";
         case 5:
            return "SHLIB";
         case 6:
            return "PHDR";
         case 7:
            return "TLS";
         case 1610612736:
            return "LOOS";
         case 1685382480:
            return "GNU_EH_FRAME";
         case 1685382481:
            return "GNU_STACK";
         case 1685382482:
            return "GNU_RELRO";
         case 1685382483:
            return "GNU_PROPERTY";
         case 1879048191:
            return "HIOS";
         case 1879048192:
            return "LOPROC";
         case Integer.MAX_VALUE:
            return "HIPROC";
         default:
            String var2 = null;
            if (var0 >= 1879048192 && var0 <= Integer.MAX_VALUE) {
               switch (var1) {
                  case 8:
                  case 10:
                     var2 = getMipsSegmentType(var0);
                     break;
                  case 15:
                     var2 = getPariscSegmentType(var0);
                     break;
                  case 40:
                     var2 = getArmSegmentType(var0);
                     break;
                  case 50:
                     var2 = getIa64SegmentType(var0);
               }

               if (var2 == null) {
                  var2 = "LOPROC+" + Integer.toHexString(var0 - 1879048192) + "h";
               }

               return var2;
            } else if (var0 >= 1610612736 && var0 <= 1879048191) {
               switch (var1) {
                  case 15:
                     var2 = getPariscSegmentType(var0);
                     break;
                  case 50:
                     var2 = getIa64SegmentType(var0);
               }

               if (var2 == null) {
                  var2 = "LOOS+" + Integer.toHexString(var0 - 1879048192) + "h";
               }

               return var2;
            } else {
               return Integer.toHexString(var0);
            }
      }
   }

   private static String getArmSegmentType(int var0) {
      switch (var0) {
         case 1879048193:
            return "EXIDX";
         default:
            return null;
      }
   }

   private static String getMipsSegmentType(int var0) {
      switch (var0) {
         case 1879048192:
            return "REGINFO";
         case 1879048193:
            return "RTPROC";
         case 1879048194:
            return "OPTIONS";
         default:
            return null;
      }
   }

   private static String getPariscSegmentType(int var0) {
      switch (var0) {
         case 1610612736:
            return "HP_TLS";
         case 1610612737:
            return "HP_CORE_NONE";
         case 1610612738:
            return "HP_CORE_VERSION";
         case 1610612739:
            return "HP_CORE_KERNEL";
         case 1610612740:
            return "HP_CORE_COMM";
         case 1610612741:
            return "HP_CORE_PROC";
         case 1610612742:
            return "HP_CORE_LOADABLE";
         case 1610612743:
            return "HP_CORE_STACK";
         case 1610612744:
            return "HP_CORE_SHM";
         case 1610612745:
            return "HP_CORE_MMF";
         case 1610612752:
            return "HP_PARALLEL";
         case 1610612753:
            return "HP_FASTBIND";
         case 1610612754:
            return "HP_OPT_ANNOT";
         case 1610612755:
            return "HP_HSL_ANNOT";
         case 1610612756:
            return "HP_STACK";
         case 1610612757:
            return "HP_CORE_UTSNAME";
         case 1879048192:
            return "PARISC_ARCHEXT";
         case 1879048193:
            return "PARISC_UNWIND";
         case 1879048194:
            return "PARISC_WEAKORDER";
         default:
            return null;
      }
   }

   private static String getIa64SegmentType(int var0) {
      switch (var0) {
         case 1610612736:
            return "HP_TLS";
         case 1610612754:
            return "HP_OPT_ANNOT";
         case 1610612755:
            return "HP_HSL_ANNOT";
         case 1610612756:
            return "HP_STACK";
         case 1879048192:
            return "IA_64_ARCHEXT";
         case 1879048193:
            return "IA_64_UNWIND";
         default:
            return null;
      }
   }

   public static String getPFString(int var0) {
      StringBuilder var1 = new StringBuilder();
      if ((var0 & 4) != 0) {
         var1.append('R');
         var0 &= -5;
      }

      if ((var0 & 2) != 0) {
         var1.append('W');
         var0 &= -3;
      }

      if ((var0 & 1) != 0) {
         var1.append('X');
         var0 &= -2;
      }

      if (var0 != 0) {
         if (var1.length() > 0) {
            var1.append('+');
         }

         Strings.ff("0x%X", var0);
      }

      return var1.toString();
   }

   public static String getSTBString(int var0) {
      switch (var0) {
         case 0:
            return "LOCAL";
         case 1:
            return "GLOBAL";
         case 2:
            return "WEAK";
         case 13:
            return "LOPROC";
         case 15:
            return "HIPROC";
         default:
            return Integer.toHexString(var0);
      }
   }

   public static String getSTTString(int var0) {
      switch (var0) {
         case 0:
            return "NOTYPE";
         case 1:
            return "OBJECT";
         case 2:
            return "FUNC";
         case 3:
            return "SECTION";
         case 4:
            return "FILE";
         case 5:
            return "COMMON";
         case 6:
            return "TLS";
         case 7:
         case 8:
         case 9:
         default:
            return Integer.toHexString(var0);
         case 10:
            return "GNU_IFUNC";
      }
   }

   public static String getSTVString(int var0) {
      switch (var0) {
         case 0:
            return "DEFAULT";
         case 1:
            return "INTERNAL";
         case 2:
            return "HIDDEN";
         case 3:
            return "PROTECTED";
         default:
            return Integer.toHexString(var0);
      }
   }

   public static String getNoteTypeString(String var0, int var1) {
      byte var3 = -1;
      switch (var0.hashCode()) {
         case 70734:
            if (var0.equals("GNU")) {
               var3 = 0;
            }
         default:
            switch (var3) {
               case 0:
                  switch (var1) {
                     case 1:
                        return "NT_GNU_ABI_TAG";
                     case 2:
                        return "NT_GNU_HWCAP";
                     case 3:
                        return "NT_GNU_BUILD_ID (unique build ID)";
                     case 4:
                        return "NT_GNU_GOLD_VERSION (gold linker version)";
                     case 5:
                        return "NT_GNU_PROPERTY_TYPE_0";
                  }
               default:
                  return Integer.toHexString(var1);
            }
      }
   }

   public static String getNoteGnuABIString(byte[] var0, ByteOrder var1) {
      ByteBuffer var2 = ByteBuffer.wrap(var0).order(var1);
      StringBuilder var3 = new StringBuilder();

      try {
         int var4 = var2.getInt();
         int var5 = var2.getInt();
         int var6 = var2.getInt();
         int var7 = var2.getInt();
         var3.append("OS: ");
         switch (var4) {
            case 0:
               var3.append("Linux");
               break;
            case 1:
               var3.append("Gnu");
               break;
            case 2:
               var3.append("Solaris2");
               break;
            case 3:
               var3.append("FreeBSD");
               break;
            default:
               return null;
         }

         var3.append(", ABI: ");
         var3.append(Integer.toString(var5));
         var3.append(".");
         var3.append(Integer.toString(var6));
         var3.append(".");
         var3.append(Integer.toString(var7));
      } catch (BufferUnderflowException var8) {
         return null;
      }

      return var3.toString();
   }

   public static String getNoteAndroidVersionString(byte[] var0, ByteOrder var1) {
      ByteBuffer var2 = ByteBuffer.wrap(var0).order(var1);
      StringBuilder var3 = new StringBuilder();
      int var4 = 0;

      try {
         var4 = var2.getInt();
         byte[] var5 = new byte[64];
         var2.get(var5);
         byte[] var6 = new byte[64];
         var2.get(var6);
         var3.append("Android API version:");
         var3.append(Integer.toString(var4));
         var3.append(" - NDK version:");
         var3.append(Strings.trim(new String(var5, Charset.forName("ASCII"))));
         var3.append(" - NDK build number:");
         var3.append(Strings.trim(new String(var6, Charset.forName("ASCII"))));
      } catch (BufferUnderflowException var7) {
         if (var4 != 0) {
            var3.append("Android API version:");
            var3.append(Integer.toString(var4));
            return var3.toString();
         }

         return null;
      }

      return var3.toString();
   }

   public static String getArmAttributeTagString(int var0) {
      switch (var0) {
         case 4:
            return "Tag_CPU_raw_name";
         case 5:
            return "Tag_CPU_name";
         case 6:
            return "Tag_CPU_arch";
         case 7:
            return "Tag_CPU_arch_profile";
         case 8:
            return "Tag_ARM_ISA_use";
         case 9:
            return "Tag_THUMB_ISA_use";
         case 10:
            return "Tag_FP_arch";
         case 11:
            return "Tag_WMMX_arch";
         case 12:
            return "Tag_Advanced_SIMD_arch";
         case 13:
            return "Tag_PCS_config";
         case 14:
            return "Tag_ABI_PCS_R9_use";
         case 15:
            return "Tag_ABI_PCS_RW_data";
         case 16:
            return "Tag_ABI_PCS_RO_data";
         case 17:
            return "Tag_ABI_PCS_GOT_use";
         case 18:
            return "Tag_ABI_PCS_wchar_t";
         case 19:
            return "Tag_ABI_FP_rounding";
         case 20:
            return "Tag_ABI_FP_denormal";
         case 21:
            return "Tag_ABI_FP_exceptions";
         case 22:
            return "Tag_ABI_FP_user_exceptions";
         case 23:
            return "Tag_ABI_FP_number_model";
         case 24:
            return "Tag_ABI_align_needed";
         case 25:
            return "Tag_ABI_align_preserved";
         case 26:
            return "Tag_ABI_enum_size";
         case 27:
            return "Tag_ABI_HardFP_use";
         case 28:
            return "Tag_ABI_VFP_args";
         case 29:
            return "Tag_ABI_WMMX_args";
         case 30:
            return "Tag_ABI_optimization_goals";
         case 31:
            return "Tag_ABI_FP_optimization_goals";
         case 32:
            return "Tag_compatibility";
         case 33:
         case 35:
         case 37:
         case 39:
         case 40:
         case 41:
         case 43:
         case 45:
         case 47:
         case 48:
         case 49:
         case 50:
         case 51:
         case 52:
         case 53:
         case 54:
         case 55:
         case 56:
         case 57:
         case 58:
         case 59:
         case 60:
         case 61:
         case 62:
         case 63:
         case 69:
         default:
            return null;
         case 34:
            return "Tag_CPU_unaligned_access";
         case 36:
            return "Tag_FP_HP_extension";
         case 38:
            return "Tag_ABI_FP_16bit_format";
         case 42:
            return "Tag_MPextension_use";
         case 44:
            return "Tag_DIV_use";
         case 46:
            return "Tag_DSP_extension";
         case 64:
            return "Tag_nodefaults";
         case 65:
            return "Tag_also_compatible_with";
         case 66:
            return "Tag_T2EE_use";
         case 67:
            return "Tag_conformance";
         case 68:
            return "Tag_Virtualization_use";
         case 70:
            return "Tag_MPextension_use_old";
      }
   }

   public static int relocate(
      int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, ELF.SymbolLocality var11
   ) {
      switch (var0) {
         case 0:
            return 0;
         case 1:
            return var5 + Force_SE(var1, var2);
         case 2:
            return var5 + var1;
         case 3:
            return var5 + var1 - var9;
         case 4:
            switch (var11) {
               case LOCAL:
                  return (var1 | var4 + 4 & -268435456) + var5 >> 2;
               case EXTERNAL:
                  return Force_SE(var1, var2) + var5 >> 2;
            }
         default:
            throw new RuntimeException(Strings.ff("Relocation type %d not recognized. Please add it", var0));
         case 5:
            return high(var3 + var5);
         case 6:
            return var3 + var5;
      }
   }

   public static int Force_SE(int var0, int var1) {
      return var0 | -1 << var1;
   }

   public static long SE(long var0, int var2) {
      return (var0 & 1 << var2 - 1) == 0L ? var0 : var0 | -1L << var2;
   }

   public static int high(int var0) {
      return var0 - (short)var0 >> 16;
   }

   public static String getX86RTString(int var0) {
      switch (var0) {
         case 0:
            return "NONE";
         case 1:
            return "32";
         case 2:
            return "PC32";
         case 3:
            return "GOT32";
         case 4:
            return "PLT32";
         case 5:
            return "COPY";
         case 6:
            return "GLOB_DAT";
         case 7:
            return "JMP_SLOT";
         case 8:
            return "RELATIVE";
         case 9:
            return "GOTOFF";
         case 10:
            return "GOTPC";
         default:
            return Strings.ff("%Xh", var0);
      }
   }

   public static boolean isRT_GLOB_DAT(ProcessorType var0, int var1) {
      if (var0 != null) {
         if (var0.isARM()) {
            return var1 == 21 || var1 == 1025;
         }

         if (var0.isIntel()) {
            return var1 == 6 || var1 == 6;
         }

         if (var0.isMIPS()) {
            return var1 == 51;
         }
      }

      return false;
   }

   public static boolean isRT_JUMP_SLOT(ProcessorType var0, int var1) {
      if (var0 != null) {
         if (var0.isARM()) {
            return var1 == 22 || var1 == 1026;
         }

         if (var0.isIntel()) {
            return var1 == 7 || var1 == 7;
         }

         if (var0.isMIPS()) {
            return var1 == 127;
         }
      }

      return false;
   }

   public static String getRTString(ProcessorType var0, int var1) {
      label19:
      if (var0 == null) {
         return Strings.ff("%Xh", var1);
      } else {
         if (var0.isARM()) {
            switch (var1) {
               case 0:
                  return "NONE";
               case 21:
                  return "GLOB_DAT";
               case 22:
                  return "JUMP_SLOT";
               case 23:
                  return "RELATIVE";
            }
         } else if (var0.isIntel()) {
            return getX86RTString(var1);
         }
         break label19;
      }
   }

   public static enum SymbolLocality {
      LOCAL,
      EXTERNAL;
   }

   public static enum WellKnownSection {
      bss(8, ".bss"),
      comment(1, ".comment"),
      ctors(1, ".ctors"),
      data(1, ".data"),
      data1(1, ".data1"),
      debug(1, ".debug"),
      debugFrame(1, ".debug_frame"),
      debugInfo(1, ".debug_info"),
      dtors(1, ".dtors"),
      dynamic(6, ".dynamic"),
      dynstr(3, ".dynstr"),
      dynsym(11, ".dynsym"),
      ehFrame(1, ".eh_frame"),
      ehFrameHdr(1, ".eh_frame_hdr"),
      fini(1, ".fini"),
      gccExceptTable(1, ".gcc_except_table"),
      gnuVersion(1879048191, ".gnu.version"),
      gnuVersion_d(1879048189, ".gnu.version_d"),
      gnuVersion_r(1879048190, ".gnu.version_r"),
      got(1, ".got"),
      hash(5, ".hash"),
      init(1, ".init"),
      interp(1, ".interp"),
      line(1, ".line"),
      note(7, ".note"),
      noteABITag(7, ".note.ABI-tag"),
      noteGnuBuildId(7, ".note.gnu.build-id"),
      noteGnuStack(1, ".note.GNU-stack"),
      plt(1, ".plt"),
      relNAME(9, ".relNAME"),
      relaNAME(4, ".relaNAME"),
      rodata(1, ".rodata"),
      rodata1(1, ".rodata1"),
      shstrtab(3, ".shstrtab"),
      strtab(3, ".strtab"),
      symtab(2, ".symtab"),
      text(1, ".text");

      final int type;
      final String name;

      private WellKnownSection(int var3, String var4) {
         this.type = var3;
         this.name = var4;
      }
   }
}
