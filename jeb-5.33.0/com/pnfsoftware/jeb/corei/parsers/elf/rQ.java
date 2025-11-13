package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeModel;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.Dwarf;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.IDIE;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.IDIEAttribute;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.Tw;
import com.pnfsoftware.jebglobal.auu;
import java.util.ArrayList;
import java.util.List;

public class rQ {
   private static final ILogger pC = GlobalLog.getLogger(rQ.class);
   private static final char[] A = new char[]{'<', '>'};
   private final ITypeManager kS;
   private final INativeCodeModel wS;
   private final INativeCodeUnit UT;
   private final IELFUnit E;

   public rQ(IELFUnit var1, INativeCodeUnit var2, INativeCodeAnalyzer var3) {
      this.E = var1;
      this.UT = var2;
      this.kS = var3.getTypeManager();
      this.wS = var3.getModel();
   }

   public void pC(List var1) {
      for (IDIE var3 : var1) {
         if (var3.getChildren().size() == 1) {
            List var4 = ((IDIE)var3.getChildren().get(0)).getChildren();

            for (IDIE var6 : var4) {
               this.pC(var6);
            }

            for (IDIE var8 : var4) {
               this.wS(var8);
            }
         }
      }
   }

   private void pC(IDIE var1) {
      String var2 = var1.getTagName();
      if (var2.equals(Dwarf.DwarfTag.DW_TAG_base_type.name())) {
         this.A(var1);
      } else if (!var2.equals(Dwarf.DwarfTag.DW_TAG_unspecified_type.name())
         && !this.pC(var2)
         && !var2.equals(Dwarf.DwarfTag.DW_TAG_typedef.name())
         && !var2.equals(Dwarf.DwarfTag.DW_TAG_array_type.name())
         && !Strings.isContainedIn(
            var2, Dwarf.DwarfTag.DW_TAG_structure_type.name(), Dwarf.DwarfTag.DW_TAG_union_type.name(), Dwarf.DwarfTag.DW_TAG_class_type.name()
         )
         && !var2.equals(Dwarf.DwarfTag.DW_TAG_enumeration_type.name())) {
         var2.equals(Dwarf.DwarfTag.DW_TAG_subroutine_type.name());
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private void A(IDIE var1) {
      String var2 = this.kS(var1);
      if (var2 != null) {
         INativeType var3 = this.kS.getType(var2);
         IDIEAttribute var4 = var1.getAttribute(Dwarf.DwarfAttribute.DW_AT_byte_size);
         IDIEAttribute var5 = var1.getAttribute(Dwarf.DwarfAttribute.DW_AT_encoding);
         if (var4 != null && var5 != null) {
            Dwarf.DwarfBaseTypeAttributeEncoding var6 = Dwarf.DwarfBaseTypeAttributeEncoding.getByValue(var5.getRawForm().intValue());
            if (var3 == null) {
               switch (var6) {
                  case DW_ATE_boolean:
                     var3 = this.kS.getType("boolean");
                     break;
                  case DW_ATE_signed_char:
                  case DW_ATE_signed:
                     var3 = this.kS.getInteger(var4.getRawForm().intValue(), true);
                     break;
                  case DW_ATE_unsigned_char:
                  case DW_ATE_unsigned:
                     var3 = this.kS.getInteger(var4.getRawForm().intValue(), false);
                     break;
                  default:
                     Object[] var10000 = new Object[]{var2, var4.getRawForm().intValue(), var6};
                     return;
               }

               Object[] var8 = new Object[]{var2, var3.getName()};
               this.kS.createAlias(var2, var3);
            }
         }
      }
   }

   private boolean pC(String var1) {
      return Strings.isContainedIn(
         var1,
         Dwarf.DwarfTag.DW_TAG_atomic_type.name(),
         Dwarf.DwarfTag.DW_TAG_const_type.name(),
         Dwarf.DwarfTag.DW_TAG_immutable_type.name(),
         Dwarf.DwarfTag.DW_TAG_packed_type.name(),
         Dwarf.DwarfTag.DW_TAG_pointer_type.name(),
         Dwarf.DwarfTag.DW_TAG_reference_type.name(),
         Dwarf.DwarfTag.DW_TAG_restrict_type.name(),
         Dwarf.DwarfTag.DW_TAG_rvalue_reference_type.name(),
         Dwarf.DwarfTag.DW_TAG_shared_type.name(),
         Dwarf.DwarfTag.DW_TAG_volatile_type.name()
      );
   }

   private IDIE pC(IDIE var1, List var2) {
      String var3 = var1.getTagName();
      IDIEAttribute var4 = var1.getAttribute(Dwarf.DwarfAttribute.DW_AT_type);
      if (var3.equals(Dwarf.DwarfTag.DW_TAG_pointer_type.name())) {
         var2.add("*");
      } else if (Dwarf.DwarfTag.DW_TAG_reference_type.name().equals(var3)) {
         var2.add("&");
      } else if (Dwarf.DwarfTag.DW_TAG_rvalue_reference_type.name().equals(var3)) {
         var2.add("&&");
      } else {
         if (Dwarf.DwarfTag.DW_TAG_ptr_to_member_type.name().equals(var3)) {
            return null;
         }

         if (Dwarf.DwarfTag.DW_TAG_friend.name().equals(var3)) {
            var1.getAttribute(Dwarf.DwarfAttribute.DW_AT_friend);
            return null;
         }

         if (!this.pC(var3)) {
            if (var4 == null) {
               return var1;
            }

            if (Dwarf.DwarfTag.DW_TAG_typedef.name().equals(var3)) {
               ArrayList var5 = new ArrayList();
               IDIE var6 = this.pC(var4.getReference(), var5);
               String var7 = this.kS(var6);
               if (!Strings.isBlank(var7)) {
                  var2.addAll(var5);
                  return var6;
               }
            }

            return var1;
         }

         var2.add(" " + var3.substring("DW_TAG_".length(), var3.lastIndexOf("_")));
      }

      return var4 == null ? var1 : this.pC(var4.getReference(), var2);
   }

   private String kS(IDIE var1) {
      String var2 = var1.getFullName();
      return var2 == null
         ? null
         : var2.replace("long int", "long")
            .replace("short int", "short")
            .replace("long unsigned int", "unsigned long")
            .replace("long unsigned long", "unsigned long long");
   }

   private void wS(IDIE var1) {
      if (var1.getTagName().equals(Dwarf.DwarfTag.DW_TAG_namespace.name())) {
         for (IDIE var3 : var1.getChildren()) {
            String var9 = var1.getAttributeName();
            if (var9 == null) {
               pC.error("Can not find namespace for DIE at offset %xh", var1.getOffset());
               return;
            }

            this.wS(var3);
         }
      } else {
         if (var1.getTagName().equals(Dwarf.DwarfTag.DW_TAG_subprogram.name())) {
            IDIEAttribute var2 = var1.getAttribute(Dwarf.DwarfAttribute.DW_AT_low_pc);
            if (var2 == null) {
               return;
            }

            String var4 = this.UT(var1);
            if (var4 == null) {
               return;
            }

            long var5 = this.UT.getVirtualImageBase() - this.E.getLoaderInformation().getImageBase() + (Long)var2.getForm();
            auu var7 = ((Tw)this.wS).E(var5);
            if (var7 != null) {
               var7.wS(var4.toString());
               Object[] var10000 = new Object[]{var5, var4};
            } else {
               Object[] var10 = new Object[]{var5, var4};
            }
         }

         Strings.isContainedIn(var1.getTagName(), Dwarf.DwarfTag.DW_TAG_inlined_subroutine.name(), Dwarf.DwarfTag.DW_TAG_entry_point.name());
      }
   }

   private String UT(IDIE var1) {
      String var2 = var1.getFullName();
      if (var2 == null) {
         IDIEAttribute var11 = var1.getAttribute(Dwarf.DwarfAttribute.DW_AT_linkage_name);
         if (var11 != null && var11.getType() == Dwarf.DwarfFormType.string) {
            return var11.getForm().toString();
         } else {
            pC.error("Can not find routine name for DIE at offset %xh (CU Offset: %xh)", var1.getOffset(), var1.getCompileUnit().getOffset());
            return null;
         }
      } else {
         StringBuilder var3 = new StringBuilder();
         Object var4 = null;
         CharSequence var5 = this.pC(var1, (String)var4, false);
         var3.append(var5).append(" ");
         var3.append(var2);
         boolean var6 = true;

         for (IDIE var8 : var1.getChildren()) {
            if (var8.getTagName().equals(Dwarf.DwarfTag.DW_TAG_formal_parameter.name())) {
               String var9 = var8.getAttributeName();
               if (!Strings.isContainedIn(var9, "this")) {
                  if (var6) {
                     var3.append("(");
                     var6 = false;
                  } else {
                     var3.append(", ");
                  }

                  CharSequence var10 = this.pC(var8, (String)var4, true);
                  var3.append(var10).append(" ").append(var9);
               }
            }
         }

         if (var6) {
            var3.append("()");
         } else {
            var3.append(")");
         }

         return var3.toString();
      }
   }

   private CharSequence pC(IDIE var1, String var2, boolean var3) {
      StringBuilder var4 = new StringBuilder();
      IDIEAttribute var5 = var1.getAttribute(Dwarf.DwarfAttribute.DW_AT_type);
      if (var5 == null) {
         return var3 ? "<?>" : "void";
      } else {
         IDIE var6 = var5.getReference();
         if (var6 == null) {
            return var3 ? "<?>" : "void";
         } else {
            ArrayList var7 = new ArrayList();
            var6 = this.pC(var6, var7);
            String var8;
            if (var6 == null) {
               var8 = "void";
            } else {
               var8 = this.kS(var6);
               if (var8 == null) {
                  var8 = "void";
               }
            }

            if (var2 != null && var8.startsWith(var2)) {
               int var9 = Strings.indexOfNotInGroup(var8, ':', var2.length(), A);
               if (var9 == -1) {
                  var8 = var8.substring(var2.length());
               }
            }

            var4.append(var8);

            for (int var11 = var7.size() - 1; var11 >= 0; var11--) {
               var4.append((String)var7.get(var11));
            }

            return var4;
         }
      }
   }
}
