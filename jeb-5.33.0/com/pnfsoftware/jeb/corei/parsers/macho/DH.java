package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.util.format.Strings;

public class DH {
   public static String pC(int var0) {
      switch (var0) {
         case -1:
            return "any";
         case 1:
            return "vax";
         case 2:
            return "romp";
         case 4:
            return "ns32032";
         case 5:
            return "ns32332";
         case 6:
            return "mc680x0";
         case 7:
            return "i386";
         case 8:
            return "mips";
         case 9:
            return "ns32532";
         case 11:
            return "hppa";
         case 12:
            return "arm";
         case 13:
            return "mc88000";
         case 14:
            return "sparc";
         case 15:
            return "i860";
         case 16:
            return "i860little";
         case 17:
            return "rs6000";
         case 18:
            return "powerpc";
         case 255:
            return "veo";
         case 16777223:
            return "amd64";
         case 16777228:
            return "arm64";
         case 16777234:
            return "powerpc64";
         default:
            return Strings.ff("unk%Xh", var0);
      }
   }

   public static String pC(int var0, int var1) {
      switch (var0) {
         case 7:
         case 16777223:
            switch (var1 & 2147483647) {
               case 3:
                  return "all";
               case 4:
                  return "486";
               case 5:
                  return "pent";
               case 8:
                  return "haswell";
               case 10:
                  return "pent4";
               case 22:
                  return "pentpro";
               case 54:
                  return "pent2m3";
               case 86:
                  return "pent2m5";
               case 132:
                  return "486sx";
               default:
                  return Strings.ff("unk%Xh", var1);
            }
         case 8:
            switch (var1) {
               case 0:
                  return "all";
               case 1:
                  return "r2300";
               case 2:
                  return "r2600";
               case 3:
                  return "r2800";
               case 4:
                  return "r2000a";
               default:
                  return Strings.ff("unk%Xh", var1);
            }
         case 12:
            switch (var1) {
               case 0:
                  return "all";
               case 1:
                  return "a500arch";
               case 2:
                  return "a500";
               case 3:
                  return "a440";
               case 4:
                  return "m4";
               case 5:
                  return "v4t";
               case 6:
                  return "v6";
               case 7:
                  return "v5tej";
               case 8:
                  return "xscale";
               case 9:
                  return "v7";
               case 10:
                  return "v7f";
               case 11:
                  return "v7s";
               case 12:
                  return "v7k";
               case 13:
                  return "v8";
               case 14:
                  return "v6m";
               case 15:
                  return "v7m";
               case 16:
                  return "v7em";
               default:
                  return Strings.ff("unk%Xh", var1);
            }
         case 18:
            switch (var1) {
               case 0:
                  return "all";
               case 1:
                  return "601";
               case 2:
                  return "602";
               case 3:
                  return "603";
               case 4:
                  return "603e";
               case 5:
                  return "603ev";
               case 6:
                  return "604";
               case 7:
                  return "604e";
               case 8:
                  return "620";
               case 9:
                  return "750";
               case 10:
                  return "7400";
               case 11:
                  return "7450";
               case 100:
                  return "970";
               default:
                  return Strings.ff("unk%Xh", var1);
            }
         case 16777228:
            switch (var1) {
               case 0:
                  return "all";
               case 1:
                  return "v8";
            }
      }

      return Strings.ff("unk%Xh", var1);
   }
}
