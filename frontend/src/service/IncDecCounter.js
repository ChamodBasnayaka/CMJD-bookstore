import { useState } from "react";
import { Button } from "react-bootstrap";
function IncDecCounter({onNumChange}) {
    const [num, setNum] = useState(1);
    const incNum = () => {
        if (num < 20) {
            setNum(Number(num) + 1);
            onNumChange(num+1)
        }
    };
    const decNum = () => {
        if (num > 1) {
            setNum(Number(num) - 1);
            onNumChange(num-1);
        }
    }
    const handleChange = (e) => {
        const amount = Number(e.target.value)
        setNum(amount);
        onNumChange(amount);
    }

    return (
        <>
            <div className="col-xl-1 inc-dec ">
                <div className="input-group">
                    <div className="input-group-prepend">
                        <Button variant="secondary btn-dec" className="mb-3" type="button" onClick={decNum}>-</Button>
                    </div>
                    <input type="text" className="form-control mb-3 text-center rounded" value={num} onChange={handleChange} />
                    <div className="input-group-prepend">
                        <Button variant="secondary btn-inc" className="mb-3 align-middle" type="button" onClick={incNum}>+</Button>
                    </div>
                </div>
            </div>
        </>
    );
}

export default IncDecCounter;