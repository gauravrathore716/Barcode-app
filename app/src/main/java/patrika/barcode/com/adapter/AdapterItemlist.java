package patrika.barcode.com.adapter;

    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.ViewGroup;
    import androidx.annotation.NonNull;
    import androidx.databinding.DataBindingUtil;
    import androidx.databinding.ViewDataBinding;
    import androidx.recyclerview.widget.RecyclerView;
    import java.util.ArrayList;
    import patrika.barcode.com.R;
    import patrika.barcode.com.activity.ActivityDetail;
    import patrika.barcode.com.databinding.LayoutListBinding;
    import patrika.barcode.com.model.DepthList;

    public class AdapterItemlist extends RecyclerView.Adapter<AdapterItemlist.MyViewHolder>{

        private Context context;
        ArrayList<DepthList.ResponseBean.DataBean> list = new ArrayList<>();


        public AdapterItemlist(ActivityDetail context, ArrayList<DepthList.ResponseBean.DataBean> list) {
            this.context = context;
            this.list= list;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          ViewDataBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.layout_list,parent,false);
          return  new  MyViewHolder(dataBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


            LayoutListBinding binding = (LayoutListBinding)holder.getBinding();
            binding.txtConter.setText(""+list.get(position).getContainerno());
            binding.txtSuppierNo.setText(""+list.get(position).getSupplierreelno());
            binding.txtgrooswt.setText(""+list.get(position).getSupp_net_wt());
            binding.txtsppnetwt.setText(""+list.get(position).getSupp_net_wt());
            binding.txtreel.setText( ""+list.get(position).getReel_gsm());
            binding.txtreelmm.setText(""+list.get(position).getReel_mm());
          //  binding.txtreellenth.setText(""+list.get(position).getReel_lenght());
            binding.txtgrooswtt.setText(""+list.get(position).getSupp_gross_wt());
            binding.txtDepthcut.setText(""+list.get(position).getDepthcut());
            binding.txtDepthcutreason.setText(""+list.get(position).getDamagereason());
            binding.pressrealno.setText(""+list.get(position).getPressreelno());

    //        if (position==0){
    //            binding.card.setBackgroundColor(context.getResources().getColor(R.color.colorAccentSecondary));
    //        }
    //        if (position==1){
    //            binding.card.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
    //        }

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            ViewDataBinding binding;

            public MyViewHolder(ViewDataBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
                this.binding.executePendingBindings();
            }
            public ViewDataBinding getBinding() {
                return binding;
            }
        }

    }

