package com.pnfsoftware.jeb.util.graph;

public interface IAddressableDigraphBuilder {
   Digraph buildModel();

   String getAddressForVertexId(int var1);

   Integer getVertexIdForAddress(String var1);
}
