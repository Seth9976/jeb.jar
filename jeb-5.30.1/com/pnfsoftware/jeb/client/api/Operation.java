package com.pnfsoftware.jeb.client.api;

public enum Operation {
   CUT,
   COPY,
   PASTE,
   SELECT_ALL,
   FIND,
   FIND_NEXT,
   DELETE,
   CLEAR,
   PROPERTIES,
   REFRESH,
   JUMP_TO,
   ITEM_FOLLOW,
   ITEM_PREVIOUS,
   ITEM_NEXT,
   NAVIGATE_FORWARD,
   NAVIGATE_BACKWARD,
   ZOOM_IN,
   ZOOM_OUT,
   ZOOM_RESET,
   CENTER,
   VIEW,
   VIEW_NEW,
   PARSE_AT,
   EXTRACT_TO,
   COPY_ADDRESS,
   TREE_COLLAPSE_ALL,
   TREE_EXPAND_ALL,
   TREE_COLLAPSE,
   TREE_EXPAND,
   REQUEST_ASSISTANT;

   @Override
   public String toString() {
      String var1 = super.toString().replace('_', ' ');
      return Character.toUpperCase(var1.charAt(0)) + var1.substring(1).toLowerCase();
   }
}
