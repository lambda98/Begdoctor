package filters

import javax.inject.Inject

import play.api.http.DefaultHttpFilters
import play.filters.cors.CORSFilter

/**
  * Created by champillon on 9/20/16.
  */
class DefaultFilters @Inject()(corsFilter: CORSFilter)
  extends DefaultHttpFilters(corsFilter)