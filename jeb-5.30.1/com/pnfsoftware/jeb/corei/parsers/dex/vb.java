package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.AbstractMetadataGroup;
import com.pnfsoftware.jeb.core.units.MetadataGroupType;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexCodeItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;
import java.util.Map;

@Ser
public class vb extends AbstractMetadataGroup {
   @SerId(1)
   private IDexUnit q;

   public vb(IDexUnit var1) {
      super("classes", MetadataGroupType.CLASSID);
      this.q = var1;
   }

   @Override
   public Map getAllData() {
      return null;
   }

   @Override
   public Object getData(String var1) {
      return this.getData(var1, AddressConversionPrecision.DEFAULT);
   }

   @Override
   public Object getData(String var1, AddressConversionPrecision var2) {
      if (var1 != null && var1.contains("->")) {
         int var3 = var1.indexOf("+");
         if (var3 >= 0) {
            var1 = var1.substring(0, var3);
         }

         IDexMethod var4 = this.q.getMethod(var1);
         if (var4 != null && var4.getData() != null && var4.getData().getCodeItem() != null) {
            IDexCodeItem var5 = var4.getData().getCodeItem();
            if (var5.hasParsingErrors()) {
               return ItemClassIdentifiers.CODE_ERRORS.getId();
            }
         }
      }

      return null;
   }

   @Override
   public boolean setData(String var1, Object var2) {
      return false;
   }

   @Override
   public List getSectionAnchorIds() {
      return null;
   }
}
