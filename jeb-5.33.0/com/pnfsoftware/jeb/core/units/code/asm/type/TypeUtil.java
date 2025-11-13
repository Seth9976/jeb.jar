package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.util.base.ISimpleFilter;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.collect.WeakIdentityHashMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jebglobal.ays;
import com.pnfsoftware.jebglobal.ayx;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class TypeUtil {
   public static final String CPP_PACKAGE_SEPARATOR = "::";
   public static final Pattern CPP_TEMPLATE_REGEXP = Pattern.compile(".*<.*>.*");
   public static final Set keywords = new HashSet(Arrays.asList("auto", "register"));
   public static final int TYPESOURCE_INUSE = 1;
   public static final int TYPESOURCE_TYPELIBS = 2;
   public static final int TYPESOURCE_ALL = 3;
   private static final WeakIdentityHashMap shortermap = new WeakIdentityHashMap();

   public static boolean isKeyword(String var0) {
      return keywords.contains(var0);
   }

   public static boolean isReservedLiteral(String var0) {
      return isKeyword(var0) || var0.equals("null") || var0.equals("true") || var0.equals("false");
   }

   private static boolean isValidName(String var0, boolean var1) {
      if (var0.length() == 0 || isReservedLiteral(var0)) {
         return false;
      } else if (!Character.isJavaIdentifierStart(var0.charAt(0))) {
         return false;
      } else {
         for (int var2 = 1; var2 < var0.length(); var2++) {
            char var3 = var0.charAt(var2);
            if (!Character.isJavaIdentifierPart(var3) && (!var1 || var3 != ' ')) {
               return false;
            }
         }

         return true;
      }
   }

   public static boolean isValidPackageName(String var0) {
      return var0 != null && !var0.isEmpty();
   }

   public static boolean isValidTypeName(String var0) {
      return var0 != null && !var0.isEmpty();
   }

   public static boolean containsCppSeparator(String var0) {
      return var0 != null && var0.contains("::");
   }

   public static boolean isValidFullyQualifiedName(String var0, List var1, String[] var2) {
      List var3 = splitCppName(var0);
      if (var3.size() == 0) {
         return false;
      } else {
         for (int var4 = 0; var4 < var3.size() - 1; var4++) {
            if (!isValidPackageName((String)var3.get(var4))) {
               return false;
            }
         }

         if (!isValidTypeName((String)var3.get(var3.size() - 1))) {
            return false;
         } else {
            if (var1 != null) {
               var1.clear();

               for (String var5 : var3) {
                  var1.add(var5);
               }
            }

            if (var2 != null) {
               var2[0] = (String)var1.get(var1.size() - 1);
            }

            return true;
         }
      }
   }

   public static List splitCppName(String var0) {
      return splitCpp(var0, ':', 2);
   }

   public static List splitCppParameters(String var0) {
      return var0.charAt(0) == '(' && var0.charAt(var0.length() - 1) == ')' ? splitCpp(var0.substring(1, var0.length() - 1), ',', 1) : splitCpp(var0, ',', 1);
   }

   private static List splitCpp(String var0, char var1, int var2) {
      ArrayList var3 = new ArrayList();
      StringBuilder var4 = new StringBuilder();
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;

      while (var7 < var0.length()) {
         char var8 = var0.charAt(var7);
         if (var8 == var1 && var5 == 0 && var6 == 0) {
            boolean var9 = true;
            if (var2 > 1) {
               for (int var10 = 1; var10 < var2; var10++) {
                  if (var7 + var10 >= var0.length() || var0.charAt(var7 + var10) != var1) {
                     var9 = false;
                     break;
                  }
               }
            }

            if (var9) {
               var3.add(var4.toString().trim());
               var4 = new StringBuilder();
               var7 += var2;
               continue;
            }
         }

         switch (var8) {
            case '(':
               var6++;
               break;
            case ')':
               var6--;
               break;
            case '<':
               var5++;
               break;
            case '>':
               var5--;
         }

         var4.append(var8);
         var7++;
      }

      if (var4.length() != 0) {
         var3.add(var4.toString().trim());
      }

      return var3;
   }

   public static String processSignature(String var0, int[] var1) {
      int var2 = -1;
      if (var0.endsWith("]")) {
         int var3 = var0.length() - 2;

         while (var3 >= 0 && var0.charAt(var3) != '[') {
            var3--;
         }

         if (var3 >= 0) {
            try {
               var2 = Integer.parseInt(var0.substring(var3 + 1, var0.length() - 1));
            } catch (NumberFormatException var5) {
            }
         }

         if (var2 <= 0) {
            return null;
         }

         var0 = var0.substring(0, var3);
      }

      int var7 = 0;

      int var4;
      for (var4 = var0.length() - 1; var4 >= 0 && var0.charAt(var4) == '*'; var4--) {
         var7++;
      }

      var0 = var0.substring(0, var4 + 1).trim();
      if (var1 != null) {
         var1[0] = var7;
         var1[1] = var2;
      }

      return var0;
   }

   public static String normalizeSignature(boolean var0, String var1, List var2, int[] var3) {
      if (!var0) {
         var1 = preNormalizeSignature(var1);
      }

      String var4 = processSignature(var1, var3);
      if (var4 == null) {
         return null;
      } else {
         return !isValidFullyQualifiedName(var4, var2, null) ? null : var1;
      }
   }

   public static String preNormalizeSignature(String var0) {
      var0 = var0.trim();
      var0 = var0.replace(".", "::").replace("/", "::");
      if (var0.startsWith("::")) {
         var0 = var0.substring("::".length());
      }

      return var0.replaceAll("\\s+", " ");
   }

   public static String buildFullyQualifiedTypeNameFromElements(List var0) {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (String var4 : var0) {
         if (var2 >= 1) {
            var1.append("::");
         }

         var1.append(var4);
         var2++;
      }

      return var1.toString();
   }

   public static INativeType buildQuick(ITypeManager var0, String var1) {
      return (INativeType)(var1.indexOf(40) >= 0 && var1.endsWith("") ? buildQuickPrototype(var0, var1) : buildQuickType(var0, var1));
   }

   public static INativeType buildQuickType(ITypeManager var0, String var1) {
      var1 = preNormalizeSignature(var1);
      int[] var2 = new int[2];
      String var3 = processSignature(var1, var2);
      if (var3 == null) {
         return null;
      } else {
         ArrayList var4 = new ArrayList();
         if (!isValidFullyQualifiedName(var3, var4, null)) {
            return null;
         } else {
            INativeType var5 = var0.getType(var3);
            if (var5 == null) {
               return null;
            } else {
               int var7 = var2[0];
               Object var6;
               if (var7 == 0) {
                  var6 = var5;
               } else {
                  var6 = var0.createReference(var5, var7);
               }

               if (var2[1] >= 0) {
                  var6 = var0.createArray((INativeType)var6, var2[1]);
               }

               return (INativeType)var6;
            }
         }
      }
   }

   public static INativeType buildArrayType(ITypeManager var0, String var1, int... var2) {
      Object var3 = var0.getType(var1);
      if (var3 == null) {
         return null;
      } else {
         for (int var4 = var2.length - 1; var4 >= 0; var4--) {
            int var5 = var2[var4];
            if (var5 <= 0) {
               return null;
            }

            var3 = var0.createArray((INativeType)var3, var5);
         }

         return (INativeType)var3;
      }
   }

   public static boolean isPrimitive(INativeType var0) {
      return var0 instanceof IPrimitiveType;
   }

   public static boolean isReference(INativeType var0) {
      return var0 instanceof IReferenceType;
   }

   public static boolean isPrototype(INativeType var0) {
      return var0 instanceof IPrototypeItem;
   }

   public static boolean isSimple(INativeType var0) {
      return isPrimitive(var0) || isReference(var0) || isPrototype(var0);
   }

   public static boolean isAlias(INativeType var0) {
      return var0 instanceof IAliasType;
   }

   public static INativeType getNonAlias(INativeType var0) {
      if (var0 instanceof IAliasType) {
         INativeType var1 = ((IAliasType)var0).getAliasedType();
         return getNonAlias(var1);
      } else {
         return var0;
      }
   }

   public static INativeType getNonAlias(INativeType var0, Class var1) {
      if (var0 instanceof IAliasType) {
         INativeType var2 = ((IAliasType)var0).getAliasedType();
         return getNonAlias(var2, var1);
      } else {
         return var0;
      }
   }

   public static boolean checkAliasedType(INativeType var0, INativeType var1) {
      if (var0 == var1) {
         return true;
      } else if (var0 instanceof IAliasType) {
         INativeType var2 = ((IAliasType)var0).getAliasedType();
         return checkAliasedType(var2, var1);
      } else {
         return false;
      }
   }

   public static boolean isBitfieldCompatible(INativeType var0) {
      return getEquivalentBitfieldTypeSize(var0) != 0;
   }

   public static boolean areBitfielTypesEquivalent(INativeType var0, INativeType var1) {
      int var2 = getEquivalentBitfieldTypeSize(var0);
      return var2 != 0 && var2 == getEquivalentBitfieldTypeSize(var1);
   }

   private static int getEquivalentBitfieldTypeSize(INativeType var0) {
      var0 = getNonAlias(var0);
      if (var0 instanceof IEnumerationType) {
         var0 = var0.getTypeManager().getType("int");
      }

      return !(var0 instanceof IPrimitiveType)
            || ((IPrimitiveType)var0).getCategory() != PrimitiveCategory.INTEGER && ((IPrimitiveType)var0).getCategory() != PrimitiveCategory.UNSIGNED
         ? 0
         : var0.getSize();
   }

   public static boolean isPointer(INativeType var0) {
      INativeType var1 = getNonAlias(var0);
      return var1 instanceof IReferenceType;
   }

   public static boolean isPointerToPointer(INativeType var0) {
      INativeType var1 = getNonAlias(var0);
      if (var1 instanceof IReferenceType) {
         INativeType var2 = ((IReferenceType)var1).getPointedType();
         return var2 instanceof IReferenceType;
      } else {
         return false;
      }
   }

   public static boolean isCompositeType(INativeType var0) {
      INativeType var1 = getNonAlias(var0);
      return var1 instanceof IArrayType || var1 instanceof IStructureType;
   }

   public static boolean isFunctionPointer(INativeType var0) {
      return isFunctionPointer(var0, null);
   }

   public static boolean isFunctionPointer(INativeType var0, IPrototypeItem[] var1) {
      INativeType var2 = getNonAlias(var0);
      if (var2 instanceof IReferenceType) {
         INativeType var3 = ((IReferenceType)var2).getPointedType();
         if (var3 instanceof IPrototypeItem) {
            if (var1 != null) {
               var1[0] = (IPrototypeItem)var3;
            }

            return true;
         }
      }

      return false;
   }

   public static boolean isVoid(INativeType var0) {
      INativeType var1 = getNonAlias(var0);
      return var1 instanceof IPrimitiveType && var0.getTypeManager().getPrimitives().isVoid((IPrimitiveType)var1);
   }

   public static boolean isVoidPointer(INativeType var0) {
      INativeType var1 = getNonAlias(var0);
      return var1 instanceof IReferenceType ? isVoid(((IReferenceType)var1).getPointedType()) : false;
   }

   public static boolean isCharacter(INativeType var0) {
      INativeType var1 = getNonAlias(var0);
      return var1 instanceof IPrimitiveType && var0.getTypeManager().getPrimitives().isCharacter((IPrimitiveType)var1);
   }

   public static boolean isInteger(INativeType var0) {
      INativeType var1 = getNonAlias(var0);
      return var1 instanceof IPrimitiveType && var0.getTypeManager().getPrimitives().isInteger((IPrimitiveType)var1);
   }

   public static boolean isSignedInteger(INativeType var0) {
      INativeType var1 = getNonAlias(var0);
      return var1 instanceof IPrimitiveType && var0.getTypeManager().getPrimitives().isSignedInteger((IPrimitiveType)var1);
   }

   public static boolean isUnsignedInteger(INativeType var0) {
      INativeType var1 = getNonAlias(var0);
      return var1 instanceof IPrimitiveType && var0.getTypeManager().getPrimitives().isUnsignedInteger((IPrimitiveType)var1);
   }

   public static boolean isFloat(INativeType var0) {
      INativeType var1 = getNonAlias(var0);
      return var1 instanceof IPrimitiveType && var0.getTypeManager().getPrimitives().isFloat((IPrimitiveType)var1);
   }

   public static List collectTypes(IPrototypeItem var0) {
      ArrayList var1 = Lists.createArrayList();
      INativeType var2 = var0.getReturnType();
      Lists.addNonNulls(var1, var2);

      for (INativeType var4 : var0.getParameterTypes()) {
         Lists.addNonNulls(var1, var4);
      }

      return var1;
   }

   public static List findType(Collection var0, String var1, boolean var2) {
      ArrayList var3 = new ArrayList();

      for (INativeType var5 : var0) {
         String var6 = var5.getSignature(true);
         String var7 = var5.getSignature(false);
         if (var6.contains(var1) || var7.contains(var1)) {
            var3.add(var5);
            if (var2) {
               break;
            }
         }
      }

      return var3;
   }

   public static boolean structureInStructure(IStructureType var0, INativeType var1) {
      if (var1 instanceof IArrayType) {
         return structureInStructure(var0, ((IArrayType)var1).getElementType());
      } else if (var1 instanceof IAliasType) {
         return structureInStructure(var0, ((IAliasType)var1).getAliasedType());
      } else {
         if (var1 instanceof IStructureType) {
            if (var0 == var1) {
               return true;
            }

            for (IStructureTypeField var3 : ((IStructureType)var1).getFields()) {
               if (structureInStructure(var0, var3.getType())) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public static INativeType getFirstSimpleType(INativeType var0) {
      while (var0 != null) {
         var0 = getNonAlias(var0);
         if (var0 instanceof IArrayType) {
            var0 = ((IArrayType)var0).getElementType();
         } else {
            if (var0 instanceof IStructureType) {
               if (((IStructureType)var0).getFields().size() != 0) {
                  var0 = ((IStructureType)var0).getField(0).getType();
                  continue;
               }
               break;
            }

            if (!(var0 instanceof IPrimitiveType) && !(var0 instanceof IEnumerationType) && !(var0 instanceof IReferenceType)) {
               break;
            }

            if (var0 instanceof IPrimitiveType) {
               if (((IPrimitiveType)var0).getCategory() == PrimitiveCategory.COMPLEX) {
                  int var1 = var0.getSize();
                  var0 = var0.getTypeManager().getPrimitives().getExactFloatBySize(var1 / 2);
               }

               return var0;
            }

            return var0;
         }
      }

      return null;
   }

   public static INativeType getBaseType(INativeType var0) {
      INativeType var1 = null;
      if (var0 instanceof IArrayType) {
         var1 = ((IArrayType)var0).getElementType();
      } else if (var0 instanceof IReferenceType) {
         var1 = ((IReferenceType)var0).getMainType();
      } else if (var0 instanceof IPrimitiveType || var0 instanceof IPrototypeItem || var0 instanceof ayx) {
         return var0;
      }

      return var1 == var0 ? null : getBaseType(var1);
   }

   public static IStructureTypeField getStructureField(INativeType var0, String var1) {
      var0 = getNonAlias(var0);
      return !(var0 instanceof IStructureType) ? null : ((IStructureType)var0).getFieldByName(var1);
   }

   public static IStructureTypeField getStructureField(INativeType var0, int var1) {
      var0 = getNonAlias(var0);
      return !(var0 instanceof IStructureType) ? null : ((IStructureType)var0).getFieldAt(var1);
   }

   public static IPrototypeItem buildQuickPrototype(ITypeManager var0, String var1) {
      String var2 = var1.trim();
      if (var2.endsWith(";")) {
         var2 = var2.substring(0, var2.length() - 1);
      }

      if (!var2.isEmpty() && var2.charAt(var2.length() - 1) == ')') {
         int var3 = var2.indexOf(40);
         if (var3 < 0) {
            return null;
         } else {
            String var4 = var2.substring(0, var3);
            String var5 = var2.substring(var3 + 1, var2.length() - 1).trim();
            int var20 = 0;
            ICallingConvention var7 = null;
            if (var4.startsWith("<")) {
               var20 = var4.indexOf(62);
               if (var20 < 0) {
                  return null;
               }

               String var8 = var4.substring(1, var20);
               var7 = var0.getCallingConventionManager().getConvention(var8);
               var20++;
            }

            String[] var21 = var4.substring(var20).trim().split("\\s+", -1);
            String var9 = Strings.join(" ", Arrays.asList(var21));
            INativeType var10 = buildQuickType(var0, var9);
            if (var10 == null) {
               return null;
            } else {
               ArrayList var11 = new ArrayList();
               boolean var12 = false;
               if (!var5.isEmpty()) {
                  String[] var13 = var5.split(",", -1);

                  for (String var17 : var13) {
                     if (var12) {
                        return null;
                     }

                     var21 = var17.trim().split("\\s+");
                     String var18 = Strings.join(" ", var21, 0, var21.length);
                     if (var18.equals("...")) {
                        var12 = true;
                     } else {
                        INativeType var19 = buildQuickType(var0, var18);
                        if (var19 == null) {
                           return null;
                        }

                        var11.add(var19);
                     }
                  }
               }

               return var0.createPrototype(var7, var10, var11, var12 ? Arrays.asList(PrototypeAttribute.VarArg) : null);
            }
         }
      } else {
         return null;
      }
   }

   public static List retrieveAvailableTypes(INativeCodeUnit var0, int var1) {
      return retrieveAvailableTypes(var0, var1, null);
   }

   public static List retrieveAvailableTypes(INativeCodeUnit var0, int var1, ISimpleFilter var2) {
      ArrayList var3 = new ArrayList();
      if ((var1 & 1) != 0) {
         for (INativeType var5 : var0.getTypeManager().getTypes(var2)) {
            var3.add(var5);
         }
      }

      if ((var1 & 2) != 0) {
         TypeLibraryService var9 = var0.getTypeLibraryService();
         if (var9 != null) {
            for (ITypeLibrary var6 : var9.getLoadedTypeLibraries()) {
               for (INativeType var8 : var6.getTypes(var2)) {
                  var3.add(var8);
               }
            }
         }
      }

      return var3;
   }

   public static String generateRoutineSignature(String var0, IPrototypeItem var1) {
      return ((ays)var1).pC(true, var0);
   }

   public static boolean same(INativeType var0, INativeType var1) {
      if (var0 == null) {
         return var1 == null;
      } else {
         return var1 == null ? false : getNonAlias(var0).equals(getNonAlias(var1));
      }
   }

   public static TypeLayoutInfo getLayoutInfo(INativeType var0) {
      int var1 = var0.getTypeManager().getSlotSize();
      int var2 = (var0.getSize() + var1 - 1) / var1;
      if (isPointer(var0)) {
         return TypeLayoutInfo.p(var2);
      } else if (isInteger(var0)) {
         return TypeLayoutInfo.i(var2);
      } else {
         return isFloat(var0) ? TypeLayoutInfo.f(var2) : TypeLayoutInfo.c(var2);
      }
   }

   public static INativeType findShorterForm(INativeType var0) {
      if (var0 == null) {
         return null;
      } else if (!(var0 instanceof IPrimitiveType var1)) {
         return var0;
      } else {
         Object var2 = (INativeType)shortermap.get(var1);
         if (var2 != null) {
            return (INativeType)var2;
         } else {
            ITypeManager var3 = var1.getTypeManager();
            String var4 = null;
            if (var3.getCompilerType() == CompilerType.MSVC) {
               if (var1.getCategory() == PrimitiveCategory.INTEGER) {
                  switch (var1.getSize()) {
                     case 1:
                        var4 = "INT8";
                        break;
                     case 2:
                        var4 = "INT16";
                     case 3:
                     case 5:
                     case 6:
                     case 7:
                     default:
                        break;
                     case 4:
                        var4 = "INT32";
                        break;
                     case 8:
                        var4 = "INT64";
                  }
               } else if (var1.getCategory() == PrimitiveCategory.UNSIGNED) {
                  switch (var1.getSize()) {
                     case 1:
                        var4 = "UINT8";
                        break;
                     case 2:
                        var4 = "UINT16";
                     case 3:
                     case 5:
                     case 6:
                     case 7:
                     default:
                        break;
                     case 4:
                        var4 = "UINT32";
                        break;
                     case 8:
                        var4 = "UINT64";
                  }
               }
            } else if (var3.getCompilerType() == CompilerType.GCC) {
               if (var1.getCategory() == PrimitiveCategory.INTEGER) {
                  switch (var1.getSize()) {
                     case 1:
                        var4 = "int8_t";
                        break;
                     case 2:
                        var4 = "int16_t";
                     case 3:
                     case 5:
                     case 6:
                     case 7:
                     default:
                        break;
                     case 4:
                        var4 = "int32_t";
                        break;
                     case 8:
                        var4 = "int64_t";
                  }
               } else if (var1.getCategory() == PrimitiveCategory.UNSIGNED) {
                  switch (var1.getSize()) {
                     case 1:
                        var4 = "uint8_t";
                        break;
                     case 2:
                        var4 = "uint16_t";
                     case 3:
                     case 5:
                     case 6:
                     case 7:
                     default:
                        break;
                     case 4:
                        var4 = "uint32_t";
                        break;
                     case 8:
                        var4 = "uint64_t";
                  }
               }
            }

            if (var4 != null && var4.length() < var1.getName().length()) {
               var2 = var3.getType(var4);
            }

            if (var2 == null) {
               var2 = var1;
            }

            shortermap.put(var1, var2);
            return (INativeType)var2;
         }
      }
   }
}
