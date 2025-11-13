package com.pnfsoftware.jeb.core.output.code;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.text.impl.TextItem;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public class AssemblyItem extends TextItem {
   public static final long ITEM_TYPE_ADDRESS = -9151314442816847872L;
   public static final long ITEM_TYPE_REGISTER = -9079256848778919936L;
   public static final long ITEM_TYPE_MNEMONIC = -9007199254740992000L;
   public static final long ITEM_TYPE_IMMEDIATE = -8935141660703064064L;
   public static final long ITEM_TYPE_LOCAL = -8863084066665136128L;
   public static final long ITEM_TYPE_TYPE = -8791026472627208192L;
   public static final long ITEM_TYPE_METHOD = -8718968878589280256L;
   public static final long ITEM_TYPE_STRUCTFIELD = -8646911284551352320L;
   public static final long ITEM_TYPE_DATA = -8574853690513424384L;
   public static final long ITEM_TYPE_IDENT = -8502796096475496448L;
   public static final long ITEM_TYPE_CLASS = -8430738502437568512L;
   public static final long ITEM_TYPE_FIELD = -8358680908399640576L;
   public static final long ITEM_TYPE_PACKAGE = -8286623314361712640L;

   public AssemblyItem(int var1, int var2) {
      super(var1, var2);
   }

   public AssemblyItem(int var1, int var2, ItemClassIdentifiers var3, long var4, int var6) {
      super(var1, var2, var3, var4, var6);
   }
}
