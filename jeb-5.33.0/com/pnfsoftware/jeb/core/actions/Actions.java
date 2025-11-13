package com.pnfsoftware.jeb.core.actions;

public class Actions {
   public static final int NOOP = 0;
   public static final int DELETE = 1;
   public static final int RENAME = 2;
   public static final int COMMENT = 3;
   public static final int QUERY_XREFS = 4;
   public static final int CONVERT = 5;
   public static final int REPLACE = 6;
   public static final int CREATE_PACKAGE = 10;
   public static final int MOVE_TO_PACKAGE = 11;
   public static final int QUERY_TYPE_HIER = 12;
   public static final int QUERY_OVERRIDES = 13;
   public static final int AUTO_RENAME_ALL = 14;
   public static final int PROVIDE_TYPE_HINT = 15;
   public static final int MOVE_TO = 16;
   public static final int COLLAPSE = 17;

   public static String idToName(int var0) {
      switch (var0) {
         case 0:
            return "Noop";
         case 1:
            return "Delete";
         case 2:
            return "Rename";
         case 3:
            return "Comment";
         case 4:
            return "QueryXrefs";
         case 5:
            return "Convert";
         case 6:
            return "Replace";
         case 7:
         case 8:
         case 9:
         default:
            return "UnknownAction" + var0;
         case 10:
            return "CreatePackage";
         case 11:
            return "MoveToPackage";
         case 12:
            return "QueryTypeHier";
         case 13:
            return "QueryOverrides";
         case 14:
            return "AutoRenameAll";
         case 15:
            return "ProvideTypeHint";
         case 16:
            return "MoveTo";
         case 17:
            return "Collapse";
      }
   }
}
