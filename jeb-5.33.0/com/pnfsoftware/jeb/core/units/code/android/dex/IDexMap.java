package com.pnfsoftware.jeb.core.units.code.android.dex;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;

@Ser
public interface IDexMap {
   int TYPE_HEADER_ITEM = 0;
   int TYPE_STRING_ID_ITEM = 1;
   int TYPE_TYPE_ID_ITEM = 2;
   int TYPE_PROTO_ID_ITEM = 3;
   int TYPE_FIELD_ID_ITEM = 4;
   int TYPE_METHOD_ID_ITEM = 5;
   int TYPE_CLASS_DEF_ITEM = 6;
   int TYPE_CALL_SITE_ID_ITEM = 7;
   int TYPE_METHOD_HANDLE_ITEM = 8;
   int TYPE_MAP_LIST = 4096;
   int TYPE_TYPE_LIST = 4097;
   int TYPE_ANNOTATION_SET_REF_LIST = 4098;
   int TYPE_ANNOTATION_SET_ITEM = 4099;
   int TYPE_CLASS_DATA_ITEM = 8192;
   int TYPE_CODE_ITEM = 8193;
   int TYPE_STRING_DATA_ITEM = 8194;
   int TYPE_DEBUG_INFO_ITEM = 8195;
   int TYPE_ANNOTATION_ITEM = 8196;
   int TYPE_ENCODED_ARRAY_ITEM = 8197;
   int TYPE_ANNOTATIONS_DIRECTORY_ITEM = 8198;
   int TYPE_HIDDENAPI_CLASS_DATA_ITEM = 61440;

   Collection getEntries();

   IDexMap.Entry getEntry(int var1);

   @Ser
   public static class Entry {
      @SerId(1)
      int id;
      @SerId(2)
      int offset;
      @SerId(3)
      int count;

      public Entry(int var1, int var2, int var3) {
         this.id = var1;
         this.offset = var2;
         this.count = var3;
      }

      public int getId() {
         return this.id;
      }

      public int getOffset() {
         return this.offset;
      }

      public int getCount() {
         return this.count;
      }
   }
}
