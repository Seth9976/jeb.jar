package com.pnfsoftware.jeb.corei.parsers.asm.type.tlgen;

import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLibraryMetadata;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLibraryService;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypelibDefinitionException;
import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

public class eo {
   private static final ILogger nf = GlobalLog.getLogger(eo.class);
   public boolean q;
   public File RF;
   public File xK;
   public File Dw;
   public TypeLibraryMetadata Uv;
   public File oW;
   public Map gO = new HashMap();

   public eo(boolean var1, boolean var2, File var3, File var4, File var5, TypeLibraryMetadata var6) {
      this.q = var1;
      this.RF = var3;
      this.xK = var4;
      this.Dw = var5;
      this.Uv = var6;
   }

   @Override
   public String toString() {
      return Strings.ff("hdr=%s;cst=%s;out=%s", this.RF, this.xK, this.Dw);
   }

   public static List q(File var0) throws IOException, TypelibDefinitionException {
      if (!Strings.endsWith(var0.getPath().toLowerCase(), ".yml", ".yaml")) {
         String var1 = Strings.decodeLocal(IO.readFile(var0));
         return Lists.createArrayList(q(var1));
      } else {
         return RF(var0);
      }
   }

   public static List RF(File var0) throws IOException, TypelibDefinitionException {
      String var1 = Strings.decodeLocal(IO.readFile(var0));
      return q(var1, var0.getParentFile());
   }

   public static List q(String var0, File var1) {
      ArrayList var2 = new ArrayList();
      Yaml var3 = new Yaml();
      Object var4 = var3.load(var0);
      if (var4 instanceof Map var5) {
         eo var6 = q(var5, var1);
         var2.add(var6);
      } else {
         if (!(var4 instanceof List)) {
            throw new TypelibDefinitionException("Unexpected definitions file");
         }

         for (Object var10 : (List)var4) {
            if (!(var10 instanceof Map var7)) {
               throw new TypelibDefinitionException("Unexpected entry in list, expected typelib definition as a map");
            }

            eo var8 = q(var7, var1);
            var2.add(var8);
         }
      }

      return var2;
   }

   public static eo q(Map var0, File var1) {
      String var2 = xK(var0, "hdrsrc");
      var2 = RF(var2);
      String var3 = xK(var0, "cstsrc");
      var3 = RF(var3);
      String var4 = xK(var0, "output");
      var4 = RF(var4);
      ArrayList var5 = new ArrayList();
      String var6 = Dw(var0, "processor");
      if (var6 != null) {
         ProcessorType var7 = ProcessorType.valueOf(var6);
         if (var7 != ProcessorType.UNKNOWN) {
            var5.add(var7);
         }
      } else {
         var5.addAll(q(var0, "processors"));
      }

      ArrayList var34 = new ArrayList();
      var6 = Dw(var0, "subsystem");
      if (var6 != null) {
         SubsystemType var8 = SubsystemType.valueOf(var6);
         if (var8 != SubsystemType.UNKNOWN) {
            var34.add(var8);
         }
      } else {
         var34.addAll(RF(var0, "subsystems"));
      }

      CompilerType var35 = CompilerType.valueOf(Dw(var0, "compiler"));
      if (var35 == CompilerType.UNKNOWN) {
         var35 = null;
      }

      int var9 = 0;

      try {
         var9 = oW(var0, "groupid");
      } catch (Exception var29) {
         try {
            String var11 = Dw(var0, "groupid");
            if (var11 != null) {
               if (!var11.startsWith("GROUPID_TYPELIB_")) {
                  var11 = "GROUPID_TYPELIB_" + var11;
               }

               var9 = TypeLibraryService.class.getField(var11).getInt(null);
            }
         } catch (Exception var28) {
            throw new TypelibDefinitionException("Illegal groupid");
         }
      }

      int var10 = 0;

      try {
         var10 = oW(var0, "uuid");
      } catch (Exception var27) {
         try {
            String var12 = Dw(var0, "uuid");
            if (var12 != null) {
               if (!var12.startsWith("UUID_TYPELIB_")) {
                  var12 = "UUID_TYPELIB_" + var12;
               }

               var10 = TypeLibraryService.class.getField(var12).getInt(null);
            }
         } catch (Exception var26) {
            throw new TypelibDefinitionException("Illegal groupid");
         }
      }

      int var36 = q(var0, "packing", Integer.MAX_VALUE);
      Map var37 = q(var0);
      double var13 = q(var0, "priority", 0.0);
      int var15 = q(var0, "version", 0);
      String var16 = xK(var0, "name");
      String var17 = xK(var0, "description");
      String var18 = xK(var0, "author");
      boolean var19 = q(var0, "enabled", true);
      boolean var20 = q(var0, "rcpcopy", false);
      if (var2 == null && var3 == null) {
         throw new TypelibDefinitionException("Missing at least one input");
      } else {
         if (var16 == null) {
            if (var4 == null) {
               throw new TypelibDefinitionException("The entry does not specify a name; it must specify an output");
            }

            var16 = IO.noExtension(new File(var4).getName());
         }

         if (var10 == 0) {
            var10 = Hash.calculateCRC32(Strings.encodeUTF8(var16));
         }

         File var21 = null;
         if (var2 != null) {
            var21 = new File(var2);
            if (!var21.isAbsolute() && var1 != null) {
               var21 = new File(var1, var2);
            }
         }

         File var22 = null;
         if (var3 != null) {
            var22 = new File(var3);
            if (!var22.isAbsolute() && var1 != null) {
               var22 = new File(var1, var3);
            }
         }

         File var23 = null;
         if (var4 != null) {
            var23 = new File(var4);
            if (!var23.isAbsolute() && var1 != null) {
               var23 = new File(var1, var4);
            }
         }

         TypeLibraryMetadata var24 = TypeLibraryMetadata.create(var5, var34, var35, var36, var9, var13, var10, var15, var16, var17, var18);
         var24.putData(var37);
         return new eo(var19, var20, var21, var22, var23, var24);
      }
   }

   private static Map q(Map var0) {
      Object var1 = var0.get("custom");
      if (var1 == null) {
         return Collections.emptyMap();
      } else if (!(var1 instanceof Map)) {
         throw new TypelibDefinitionException("custom entry must be a dict of key-value pairs");
      } else {
         HashMap var2 = new HashMap();

         for (Object var4 : ((Map)var1).keySet()) {
            if (!(var4 instanceof String)) {
               throw new TypelibDefinitionException("key in custom entry is not a string");
            }

            Object var5 = ((Map)var1).get(var4);
            if (!(var5 instanceof String) && !(var5 instanceof Integer) && !(var5 instanceof Double)) {
               throw new TypelibDefinitionException("value in custom entry is not a string, int, or double");
            }

            var2.put((String)var4, var5);
         }

         return var2;
      }
   }

   private static String RF(String var0) {
      if (var0 != null) {
         if (var0.contains("${typelibs}")) {
            String var1 = System.getenv("JEB_DEVHOME");
            Assert.a(var1 != null);
            File var2 = new File(var1, "jeb3-core/typelibs");
            var0 = var0.replace("${typelibs}", var2.getPath());
         }

         if (var0.contains("${")) {
            throw new TypelibDefinitionException("Unexpanded ${...} variables remain in path");
         }
      }

      return var0;
   }

   private static List q(Map var0, Object var1) {
      ArrayList var2 = new ArrayList();
      Object var3 = var0.get(var1);
      if (var3 instanceof String) {
         ProcessorType var4 = ProcessorType.valueOf(((String)var3).toUpperCase());
         if (var4 == ProcessorType.UNKNOWN) {
            throw new TypelibDefinitionException("Unknown processor: " + var3);
         }

         var2.add(var4);
      } else if (var3 instanceof List) {
         for (Object var5 : (List)var3) {
            ProcessorType var6 = ProcessorType.valueOf(((String)var5).toUpperCase());
            if (var6 == ProcessorType.UNKNOWN) {
               throw new TypelibDefinitionException("Unknown processor: " + var5);
            }

            var2.add(var6);
         }
      }

      return var2;
   }

   private static List RF(Map var0, Object var1) {
      ArrayList var2 = new ArrayList();
      Object var3 = var0.get(var1);
      if (var3 instanceof String) {
         SubsystemType var4 = SubsystemType.valueOf(((String)var3).toUpperCase());
         if (var4 == SubsystemType.UNKNOWN) {
            throw new TypelibDefinitionException("Unknown subsystem: " + var3);
         }

         var2.add(var4);
      } else if (var3 instanceof List) {
         for (Object var5 : (List)var3) {
            SubsystemType var6 = SubsystemType.valueOf(((String)var5).toUpperCase());
            if (var6 == SubsystemType.UNKNOWN) {
               throw new TypelibDefinitionException("Unknown subsystem: " + var5);
            }

            var2.add(var6);
         }
      }

      return var2;
   }

   private static String xK(Map var0, Object var1) {
      return (String)var0.get(var1);
   }

   private static String Dw(Map var0, Object var1) {
      String var2 = xK(var0, var1);
      return var2 == null ? null : var2.toUpperCase();
   }

   private static String Uv(Map var0, Object var1) {
      String var2 = xK(var0, var1);
      return var2 == null ? null : var2.toLowerCase();
   }

   private static Integer oW(Map var0, Object var1) {
      return (Integer)var0.get(var1);
   }

   private static int q(Map var0, Object var1, int var2) {
      Integer var3 = oW(var0, var1);
      return var3 != null ? var3 : var2;
   }

   private static Boolean gO(Map var0, Object var1) {
      return (Boolean)var0.get(var1);
   }

   private static boolean q(Map var0, Object var1, boolean var2) {
      Boolean var3 = gO(var0, var1);
      return var3 != null ? var3 : var2;
   }

   private static Double nf(Map var0, Object var1) {
      return (Double)var0.get(var1);
   }

   private static double q(Map var0, Object var1, double var2) {
      Double var4 = nf(var0, var1);
      return var4 != null ? var4 : var2;
   }

   @Deprecated
   public static eo q(String var0) {
      File var1 = null;
      File var2 = null;
      File var3 = null;
      ProcessorType var4 = null;
      SubsystemType var5 = null;
      CompilerType var6 = null;
      int var7 = Integer.MAX_VALUE;
      int var8 = 0;
      double var9 = 0.0;
      int var11 = 0;
      int var12 = 0;
      String var13 = null;
      String var14 = null;
      String var15 = null;
      int var16 = 0;

      for (String var20 : Strings.splitLines(var0)) {
         var16++;
         var20 = Strings.trim(var20);
         if (!var20.isEmpty() && !var20.startsWith(";")) {
            String[] var21 = var20.split("=");
            if (var21.length == 2) {
               String var22 = var21[0].trim();
               String var23 = var21[1].trim();
               if (!var23.isEmpty()) {
                  try {
                     switch (var22) {
                        case "hdrsrc":
                           var1 = new File(var23);
                           break;
                        case "cstsrc":
                           var2 = new File(var23);
                           break;
                        case "output":
                           var3 = new File(var23);
                           break;
                        case "processor":
                           var4 = ProcessorType.valueOf(var23.toUpperCase());
                           break;
                        case "subsystem":
                           var5 = SubsystemType.valueOf(var23.toUpperCase());
                           break;
                        case "compiler":
                           var6 = CompilerType.valueOf(var23.toUpperCase());
                           break;
                        case "packing":
                           var7 = Integer.parseInt(var23);
                           break;
                        case "groupid":
                           try {
                              var8 = Integer.parseInt(var23);
                           } catch (NumberFormatException var27) {
                              Field var26 = TypeLibraryService.class.getField(var23);
                              var8 = var26.getInt(null);
                           }
                           break;
                        case "priority":
                           var9 = Double.parseDouble(var23);
                           break;
                        case "uuid":
                           var11 = Integer.parseInt(var23);
                           break;
                        case "version":
                           var12 = Integer.parseInt(var23);
                           break;
                        case "name":
                           var13 = var23;
                           break;
                        case "description":
                           var14 = var23;
                           break;
                        case "author":
                           var15 = var23;
                           break;
                        default:
                           nf.warn("Line %d: Skipping unknown entry: %s", var16, var22);
                     }
                  } catch (Exception var28) {
                     throw new TypelibDefinitionException(Strings.ff("Line %d: Invalid entry: %s: %s", var16, var22, var23), var28);
                  }
               }
            }
         }
      }

      if (var1 == null && var2 == null) {
         throw new TypelibDefinitionException("Specify at least one of the hdrsrc or cstsrc entries");
      } else if (var4 == null) {
         throw new TypelibDefinitionException("Missing entry: processor");
      } else {
         if (var13 == null) {
            if (var3 == null) {
               throw new TypelibDefinitionException("The entry does not specify a name; it must specify an output");
            }

            var13 = IO.noExtension(var3.getName());
         }

         if (var11 == 0) {
            var11 = Hash.calculateCRC32(Strings.encodeUTF8(var13));
         }

         TypeLibraryMetadata var29 = TypeLibraryMetadata.create(var4, var5, var6, var7, var8, var9, var11, var12, var13, var14, var15);
         return new eo(true, false, var1, var2, var3, var29);
      }
   }
}
