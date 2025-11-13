package com.pnfsoftware.jebglobal;

public class ceq {
   public static boolean pC(com.pnfsoftware.jeb.corei.parsers.macho.bO var0, String var1) {
      switch (var1) {
         case "__cls_refs":
         case "__selector_refs":
         case "__objc_classlist":
         case "__objc_nlclslist":
         case "__objc_catlist":
         case "__objc_nlcatlist":
         case "__objc_selrefs":
         case "__objc_classrefs":
         case "__objc_superrefs":
         case "__objc_protolist":
         case "__objc_protorefs":
            return true;
         default:
            return false;
      }
   }

   public static boolean A(com.pnfsoftware.jeb.corei.parsers.macho.bO var0, String var1) {
      if (pC(var0, var1)) {
         return true;
      } else {
         switch (var1) {
            case "__module_info":
            case "__objc_methtype":
            case "__objc_msgrefs":
            case "__objc_imageinfo":
            case "__objc_const":
            case "__objc_ivar":
            case "__objc_data":
            case "__objc_methname":
            case "__objc_classname":
               return true;
            default:
               return false;
         }
      }
   }

   public static boolean pC(String var0) {
      return var0.startsWith("_OBJC_CLASS_$") ? true : var0.startsWith("_OBJC_METACLASS_$");
   }
}
