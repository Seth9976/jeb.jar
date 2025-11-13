package com.pnfsoftware.jeb.core.units;

public class WellKnownUnitTypes {
   public static final String typeGeneric = "generic";
   public static final String typeComposite = "composite";
   public static final String typeStream = "stream";
   public static final String typeBinary = "binary";
   public static final String typeText = "text";
   public static final String typeZipArchive = "zip";
   public static final String typeRarArchive = "rar";
   public static final String typeTar = "tar";
   public static final String typeGzip = "gzip";
   public static final String typeArArchive = "ar";
   public static final String typeSevenzipArchive = "sevenzip";
   public static final String typeLinuxElf = "elf";
   public static final String typeWinPe = "winpe";
   public static final String typeAppleMacho = "macho";
   public static final String typeAppleMachoFat = "machofat";
   public static final String typeWinCoff = "wincoff";
   public static final String typeIntelX86 = "x86";
   public static final String typeIntelX86_64 = "x86_64";
   public static final String typeARM = "arm";
   public static final String typeARM64 = "arm64";
   public static final String typeMIPS = "mips";
   public static final String typeMIPS64 = "mips64";
   public static final String typeAtmelAVR = "avr";
   public static final String typeIntelHEX = "ihex";
   public static final String typeRiscV = "riscv";
   public static final String typeJavaArchive = "jar";
   public static final String typeJavaClassfile = "javaclass";
   public static final String typeAndroidOptimizedDex = "odex";
   public static final String typeAndroidDex = "dex";
   public static final String typeAndroidApk = "apk";
   public static final String typeAndroidResources = "arsc";
   public static final String typeAndroidXApk = "xapk";
   public static final String typeAndroidAar = "aar";
   public static final String typeAndroidOat = "oat";
   public static final String typeAndroidArt = "art";
   public static final String typeAndroidVdex = "vdex";
   public static final String typeAndroidCompactDex = "cdex";
   public static final String typeChromeCrx = "crx";
   public static final String typeXml = "xml";
   public static final String typeHtml = "html";
   public static final String typeSvg = "svg";
   public static final String typeJson = "json";
   public static final String typeCertificate = "cert";
   public static final String typeC = "c";
   public static final String typeCpp = "cpp";
   public static final String typePython = "python";
   public static final String typeJava = "java";
   public static final String typeJavaScript = "javascript";
   public static final String typeVBScript = "vbscript";
   public static final String typeWebassemblyModule = "wasm";
   public static final String typeWebassemblyBytecode = "wasmbc";
   public static final String typeEthereumContract = "eth";
   public static final String typeEthereumBytecode = "evmbc";
   public static final String typePdf = "pdf";
   public static final String typeMicrosoftOle = "ole";
   public static final String typeMicrosoftWord = "msdoc";
   public static final String typeMicrosoftExcel = "msxls";
   public static final String typeMicrosoftPowerpoint = "msppt";
   public static final String typeMicrosoftWordOpenXML = "msdocx";
   public static final String typeMicrosoftExcelOpenXML = "msxlsx";
   public static final String typeMicrosoftPowerpointOpenXML = "mspptx";
   public static final String typeMicrosoftOutlook = "msoutlook";
   public static final String typeMicrosoftThumbs = "msthumbs";
   public static final String typeLLVMBitcode = "llvmbc";
   public static final String pseudotypeImage = "image";
   public static final String pfxTypeDecompiler = "dcmp_";
   public static final String pfxTypeDebugger = "dbug_";
   public static final String genDisassembler = "code_disa";
   public static final String genDecompiler = "code_dcmp";
   public static final String typeCART = "cart";

   public static boolean isNativeCode(String var0) {
      if (var0 == null) {
         return false;
      } else {
         switch (var0) {
            case "x86":
            case "x86_64":
            case "arm":
            case "arm64":
            case "mips":
            case "mips64":
            case "avr":
            case "riscv":
               return true;
            default:
               return false;
         }
      }
   }

   public static String toCommonExtension(String var0) {
      if (var0 == null) {
         return "";
      } else if (var0.startsWith("dcmp_")) {
         return "src";
      } else if (var0.startsWith("dbug_")) {
         return "";
      } else {
         switch (var0) {
            case "generic":
            case "composite":
            case "stream":
            case "binary":
            case "elf":
            case "winpe":
            case "macho":
            case "machofat":
            case "wincoff":
            case "arm":
            case "arm64":
            case "mips":
            case "mips64":
            case "riscv":
            case "avr":
            case "ihex":
               return "bin";
            case "x86":
            case "x86_64":
               return "exe";
            case "text":
               return "txt";
            case "sevenzip":
               return "7z";
            case "javaclass":
               return "class";
            case "python":
               return "py";
            case "javascript":
               return "js";
            case "vbscript":
               return "vbs";
            case "msdoc":
               return "doc";
            case "msxls":
               return "xls";
            case "msppt":
               return "ppt";
            case "msdocx":
               return "docx";
            case "msxlsx":
               return "xlsx";
            case "mspptx":
               return "pptx";
            case "msoutlook":
               return "msg";
            case "msthumbs":
               return "db";
            default:
               return var0;
         }
      }
   }

   public static String fromMimeType(String var0) {
      if (var0.startsWith("image/")) {
         return "image";
      } else {
         switch (var0) {
            case "application/json":
               return "json";
            case "application/x-7z-compressed":
               return "sevenzip";
            case "application/x-tar":
               return "tar";
            case "application/xml":
               return "xml";
            case "application/zip":
               return "zip";
            case "text/plain":
               return "text";
            case "text/html":
               return "html";
            default:
               return null;
         }
      }
   }
}
