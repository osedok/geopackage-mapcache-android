package mil.nga.mapcache.listeners;

import mil.nga.mapcache.view.detail.DetailPageLayerObject;

/**
 * Listener for clicking on a layer row on the GeoPackageDetail page
 */
public interface DetailLayerClickListener {
    void onClick(DetailPageLayerObject layerObject);

}
