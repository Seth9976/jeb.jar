package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.dwarf.Dwarf;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.IDIE;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.IDIEAttribute;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.IDwCompileUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Ser
public class ciu implements IDIE {
   @SerId(1)
   private final long q;
   @SerId(2)
   private final int RF;
   @SerId(3)
   private final ciu xK;
   @SerId(4)
   private List Dw = new ArrayList();
   @SerId(5)
   private List Uv = new ArrayList();
   @SerTransient
   private String oW;

   public ciu(long var1, int var3, ciu var4) {
      this.q = var1;
      this.RF = var3;
      this.xK = var4;
   }

   public void q(IDIE var1) {
      this.Dw.add(var1);
   }

   public void q(civ var1) {
      this.Uv.add(var1);
   }

   @Override
   public long getOffset() {
      return this.q;
   }

   @Override
   public String getTagName() {
      if (this.oW == null) {
         Dwarf.DwarfTag var1 = Dwarf.DwarfTag.getByValue(this.RF);
         this.oW = var1 != null ? var1.name() : Long.toHexString(this.RF) + "h";
      }

      return this.oW;
   }

   @Override
   public String getAttributeName() {
      IDIEAttribute var1 = this.getAttribute(Dwarf.DwarfAttribute.DW_AT_name.name());
      if (var1 == null) {
         IDIE var2 = this.getSpecification();
         if (var2 == null || var2.getAttribute(Dwarf.DwarfAttribute.DW_AT_name.name()) == null) {
            return null;
         }

         var1 = var2.getAttribute(Dwarf.DwarfAttribute.DW_AT_name.name());
         if (var1 == null) {
            return null;
         }
      }

      return var1.getForm() != null ? var1.getForm().toString() : "";
   }

   @Override
   public IDIE getSpecification() {
      IDIEAttribute var1 = this.getAttribute(Dwarf.DwarfAttribute.DW_AT_specification);
      return var1 == null ? null : var1.getReference();
   }

   public IDIE q() {
      IDIEAttribute var1 = this.q(Dwarf.DwarfAttribute.DW_AT_abstract_origin.name(), false);
      return var1 == null ? null : var1.getReference();
   }

   private static void q(StringBuilder var0, IDIE var1) {
      q(var0, var1, false);
   }

   private static void q(StringBuilder var0, IDIE var1, boolean var2) {
      boolean var3 = false;

      while (
         !var3
               && Strings.isContainedIn(
                  var1.getTagName(),
                  Dwarf.DwarfTag.DW_TAG_structure_type.name(),
                  Dwarf.DwarfTag.DW_TAG_union_type.name(),
                  Dwarf.DwarfTag.DW_TAG_class_type.name()
               )
            || var1.getTagName().equals(Dwarf.DwarfTag.DW_TAG_namespace.name())
      ) {
         boolean var4 = var1.getTagName().equals(Dwarf.DwarfTag.DW_TAG_namespace.name());
         if (!var2 || var4) {
            var0.insert(0, "::").insert(0, RF(var1));
         }

         var1 = var1.getParent();
         if (var4) {
            var3 = true;
         }
      }
   }

   private static String RF(IDIE var0) {
      String var1 = var0.getAttributeName();
      if (var1 == null && var0.getTagName().equals(Dwarf.DwarfTag.DW_TAG_namespace.name())) {
         var1 = "(anonymous namespace)";
      }

      return var1;
   }

   @Override
   public String getNamespace() {
      StringBuilder var1 = new StringBuilder();
      q(var1, this.xK, true);
      if (var1.length() == 0) {
         IDIE var2 = this.getSpecification();
         if (var2 != null) {
            q(var1, var2.getParent(), true);
         }
      }

      return var1.length() == 0 ? null : var1.toString();
   }

   @Override
   public String getFullName() {
      String var1 = this.getAttributeName();
      if (var1 == null) {
         return null;
      } else {
         StringBuilder var2 = new StringBuilder();
         q(var2, this.xK);
         if (var2.length() == 0) {
            IDIE var3 = this.getSpecification();
            if (var3 != null) {
               q(var2, var3.getParent());
            }
         }

         var2.append(var1);
         return var2.toString();
      }
   }

   public ciu RF() {
      return this.xK;
   }

   @Override
   public List getChildren() {
      return this.Dw;
   }

   @Override
   public List getAttributes() {
      return this.Uv;
   }

   @Override
   public IDIEAttribute getAttribute(String var1) {
      return this.q(var1, true);
   }

   public IDIEAttribute q(String var1, boolean var2) {
      for (IDIEAttribute var4 : this.Uv) {
         if (var1.equals(var4.getName())) {
            return var4;
         }
      }

      if (var2) {
         IDIE var6 = this.q();
         if (var6 != null) {
            for (IDIEAttribute var5 : var6.getAttributes()) {
               if (var1.equals(var5.getName())) {
                  return var5;
               }
            }
         }
      }

      return null;
   }

   @Override
   public IDIEAttribute getAttribute(Dwarf.DwarfAttribute var1) {
      return this.getAttribute(var1.name());
   }

   @Override
   public String toString() {
      return "DIE [offset="
         + Long.toHexString(this.q)
         + "h, tagName="
         + this.getTagName()
         + ", childrenSize="
         + this.Dw.size()
         + (this.xK == null ? " ]" : ", parent=" + Long.toHexString(this.xK.q) + " ]");
   }

   @Override
   public IDIE getChildAtOffset(long var1) {
      int var3 = Collections.binarySearch(this.Dw, new ciu(var1, 0, null), (var0, var1x) -> Long.compare(var0.getOffset(), var1x.getOffset()));
      if (var3 >= 0) {
         return (IDIE)this.Dw.get(var3);
      } else if (var3 == -1) {
         return null;
      } else {
         var3 = -(var3 + 2);
         return ((IDIE)this.Dw.get(var3)).getChildAtOffset(var1);
      }
   }

   @Override
   public IDwCompileUnit getCompileUnit() {
      Object var1 = this.xK == null ? this : this.xK;

      while (((IDIE)var1).getParent() != null) {
         var1 = ((IDIE)var1).getParent();
      }

      if (var1 instanceof IDwCompileUnit) {
         return (IDwCompileUnit)var1;
      } else {
         throw new IllegalStateException("No Compile Unit is bound to DIE");
      }
   }

   @Override
   public IDIE getReference(IDIEAttribute var1) {
      return var1.getForm() instanceof Long ? this.getCompileUnit().getChildAtOffset((Long)var1.getForm()) : null;
   }
}
