package com.example.jourdanrodrigues.controk.Employee;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jourdanrodrigues.controk.R;

import java.util.List;

class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {
    private List<Employee> employees;
    private Listener listener;

    EmployeeAdapter(List<Employee> employees, Listener listener) {
        this.employees = employees;
        this.listener = listener;
    }

    static class EmployeeViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView name;
        TextView email;
        TextView cpf;
        TextView role;
        TextView observation;

        EmployeeViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.employee_card_view);
            name = (TextView) view.findViewById(R.id.employee_name);
            email = (TextView) view.findViewById(R.id.employee_email);
            cpf = (TextView) view.findViewById(R.id.employee_cpf);
            role = (TextView) view.findViewById(R.id.employee_role);
            observation = (TextView) view.findViewById(R.id.employee_observation);
        }
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_employee, viewGroup, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder employeeViewHolder, int i) {
        final Employee employee = employees.get(i);
        // TODO: Figure a way to display special characters correctly
        employeeViewHolder.name.setText(employee.getName());
        employeeViewHolder.email.setText(employee.getEmail());
        employeeViewHolder.cpf.setText(employee.getCpf());
        employeeViewHolder.role.setText(employee.getRole());
        employeeViewHolder.observation.setText(employee.getObservation());

        employeeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(employee);
            }
        });
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    interface Listener {
        void onClick(Employee employee);
    }
}
